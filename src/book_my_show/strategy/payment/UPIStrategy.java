package book_my_show.strategy.payment;

import book_my_show.models.Booking;

public class UPIStrategy implements PaymentStrategy {
    @Override
    public boolean pay(Booking booking) {
        System.out.println("Paid " + booking.getAmount() + "via UPI for booking id " + booking.getId());
        return true;
    }
}
