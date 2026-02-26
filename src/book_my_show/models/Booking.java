package book_my_show.models;

import java.util.List;
import java.util.UUID;

import book_my_show.enums.BookingStatus;
import book_my_show.enums.PaymentType;

public class Booking {
    private final String id;
    private final String userId;
    private final String showId;
    private final List<String> seatIds;
    private BookingStatus bookingStatus;
    private PaymentType paymentType;
    private double amount;

    public Booking(String userId, String showId, List<String> seatIds, BookingStatus bookingStatus,
            PaymentType paymentType, double amount) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.showId = showId;
        this.seatIds = seatIds;
        this.bookingStatus = bookingStatus;
        this.paymentType = paymentType;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getShowId() {
        return showId;
    }

    public List<String> getSeatIds() {
        return seatIds;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
