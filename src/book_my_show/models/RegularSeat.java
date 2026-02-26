package book_my_show.models;

import book_my_show.enums.SeatType;

public class RegularSeat extends Seat {
    public RegularSeat(double price) {
        super(price);
    }

    @Override
    public SeatType getSeatType() {
        return SeatType.BASIC;
    }
}
