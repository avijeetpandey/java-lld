package book_my_show.models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

// show contains all the information about movie and the location
public class Show {
    private final String id;
    private final Movie movie;
    private final Screen screen;
    private final Theater theater;
    private final Date start;
    private final Date end;

    public Show(Movie movie, Screen screen, Theater theater, Date start, Date end) {
        this.id = UUID.randomUUID().toString();
        this.movie = movie;
        this.screen = screen;
        this.theater = theater;
        this.start = start;
        this.end = end;
    }

    public List<Seat> getSeats() {
        return screen.getSeats();
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "Show [id=" + id + ", movie=" + movie + ", screen=" + screen + ", theater=" + theater + ", start="
                + start + ", end=" + end + ", getSeats()=" + getSeats() + ", getClass()=" + getClass() + ", hashCode()="
                + hashCode() + ", toString()=" + super.toString() + "]";
    }
}
