package book_my_show.services;

import book_my_show.models.Movie;
import book_my_show.repository.MovieRepository;

public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie createMovie(String title, int duration) {
        Movie movie = new Movie(title, duration);
        movieRepository.save(movie);
        return movie;
    }

    public Movie getMovie(String id) {
        return movieRepository.get(id);
    }
}
