package book_my_show.models;

import book_my_show.enums.SeatType;

public class VipSeat extends Seat {

    public VipSeat(double price) {
       super(price);
    }

    @Override
    public SeatType getSeatType() {
        return SeatType.VIP;
    }
}
