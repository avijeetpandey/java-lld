package book_my_show.models;

import java.util.UUID;

import book_my_show.enums.SeatType;

public abstract class Seat {
    private final String id;
    private double price;

    public Seat(double price) {
        this.id = UUID.randomUUID().toString();
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public abstract SeatType getSeatType();
}