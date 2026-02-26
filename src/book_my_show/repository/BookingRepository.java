package book_my_show.repository;

import java.util.HashMap;
import java.util.Map;

import book_my_show.models.Booking;

public class BookingRepository {
    private final Map<String, Booking> bookings = new HashMap<>();

    public void save(Booking booking) {
        bookings.put(booking.getId(), booking);
    }

    public Booking get(String id) {
        return bookings.get(id);
    }

    public void update(Booking updatedBooking) {
        if(bookings.containsKey(updatedBooking.getId())) {
            save(updatedBooking);
        } else {
            throw new RuntimeException("Booking does not exist unable to update");
        }
    }
}
