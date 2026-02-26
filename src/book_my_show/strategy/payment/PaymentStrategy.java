package book_my_show.strategy.payment;

import book_my_show.models.Booking;

public interface PaymentStrategy {
    boolean pay(Booking booking);
}
