package book_my_show.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import book_my_show.models.Theater;

public class TheatreRepository {
    private final Map<String, Theater> theatres = new HashMap<>();

    public void save(Theater theater) {
        theatres.put(theater.getId(), theater);
    }

    public Theater get(String id) {
        if(theatres.containsKey(id)) {
            return theatres.get(id);
        }

        return null;
    }

    public List<Theater> getAll() {
        return new ArrayList<>(theatres.values());
    }
}
