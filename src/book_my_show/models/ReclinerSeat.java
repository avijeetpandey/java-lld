package book_my_show.models;

import book_my_show.enums.SeatType;

public class ReclinerSeat extends Seat {
    public ReclinerSeat(double price) {
        super(price);
    }

    @Override
    public SeatType getSeatType() {
        return SeatType.RECLINER;
    }
}
