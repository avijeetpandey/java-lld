package book_my_show.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import book_my_show.models.Show;

public class ShowRepository {
    private final Map<String , Show> shows = new HashMap<>();

    public void save(Show show) {
        shows.put(show.getId(), show);
    }

    public Show get(String id) {
        if(shows.containsKey(id)) {
            return shows.get(id);
        }

        return null;
    }

    public List<Show> getAll() {
        return new ArrayList<>(shows.values());
    }
}
