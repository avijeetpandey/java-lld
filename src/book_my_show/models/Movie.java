package book_my_show.models;

import java.util.UUID;

// represents a movie in the system
public class Movie {
    private final String id;
    private final String title;
    private final int duration;

    public Movie(String title, int duration) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.duration = duration;
    }

    // getters
    public String getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

    public int getDuration() {
        return duration;
    }
}
