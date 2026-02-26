package book_my_show.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Screen {
    private final String id;
    private final Map<String, Seat> seats = new HashMap<>();

    public Screen() {
        this.id = UUID.randomUUID().toString();
    }

    public void addSeat(Seat seat) {
        seats.put(seat.getId(), seat);
    }

    public List<Seat> getSeats() {
        return new ArrayList<>(seats.values());
    }

    public String getId() {
        return this.id;
    }
}
