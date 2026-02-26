package book_my_show.repository;

import java.util.HashMap;
import java.util.Map;

import book_my_show.models.Movie;

public class MovieRepository {
    private final Map<String, Movie> movies = new HashMap<>();

    public void save(Movie movie) {
        movies.put(movie.getId(), movie);
    }

    public Movie get(String id) {
        if(movies.containsKey(id)) {
            return movies.get(id);
        }
        return null;
    }
}
