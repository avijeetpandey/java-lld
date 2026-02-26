package book_my_show.services;

import java.util.List;

import book_my_show.enums.BookingStatus;
import book_my_show.enums.PaymentType;
import book_my_show.factory.PaymentStrategyFactory;
import book_my_show.models.Booking;
import book_my_show.models.Seat;
import book_my_show.models.Show;
import book_my_show.repository.BookingRepository;
import book_my_show.strategy.locking.LockProvider;
import book_my_show.strategy.payment.PaymentStrategy;

public class BookingService {
    private final LockProvider lockProvider;
    private final BookingRepository bookingRepository;
    private static final long TTL = 5000L;

    public BookingService(LockProvider lockProvider, BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        this.lockProvider = lockProvider;
    }

    public Booking createBooking(String userId, Show show, List<String> seatIds) {
        // try to lock the seats
        for(String seatId: seatIds) {
            String key = show.getId() + ":" + seatId;
            if(!lockProvider.tryLock(key, userId, TTL)) {
                throw new RuntimeException("Seat is temproraily unavailable");
            }
        }

        double totalPrice = 0.0;

        for (Seat seat: show.getSeats()) {
            if(seatIds.contains(seat.getId())) {
                totalPrice += seat.getPrice();
            }
        }

        // create booking
        Booking booking = new Booking(userId, show.getId(), seatIds, BookingStatus.CREATED, null, totalPrice);
        
        bookingRepository.save(booking);

        System.out.println("Booking created: "+ booking.getId());

        return booking;
    }

    public void confirmBooking(Booking booking, PaymentType paymentType) {
        if(booking.getBookingStatus() != BookingStatus.CREATED) {
            throw new IllegalStateException("Booking is not in a valid state for confirmation");
        }

        for(String seatId: booking.getSeatIds()) {
            String key = booking.getId() + ":" + seatId;
            if(lockProvider.isLockExpired(key) || !lockProvider.isLockedBy(key, seatId)) {
                throw new RuntimeException("Seat is not available");
            }
        }

        booking.setPaymentType(paymentType);
        PaymentStrategy paymentStrategy = PaymentStrategyFactory.getStrategy(paymentType);

        paymentStrategy.pay(booking);

        for(String seatId: booking.getSeatIds()) {
            String key = booking.getShowId() + ":" + seatId;
            lockProvider.unlock(key);
        }

        booking.setBookingStatus(BookingStatus.CONFIRMED);
        System.out.println("Booking confirmed: "+ booking.getId());
    }
}
