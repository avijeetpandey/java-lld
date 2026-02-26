package book_my_show.services;

import java.util.Date;
import java.util.List;

import book_my_show.models.Movie;
import book_my_show.models.Screen;
import book_my_show.models.Show;
import book_my_show.models.Theater;
import book_my_show.repository.MovieRepository;
import book_my_show.repository.ScreenRepository;
import book_my_show.repository.ShowRepository;
import book_my_show.repository.TheatreRepository;

public class ShowService {
    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;
    private final ScreenRepository screenRepository;

    public ShowService(ShowRepository showRepository, MovieRepository movieRepository, TheatreRepository theatreRepository, ScreenRepository screenRepository) {
        this.showRepository = showRepository;
        this.theatreRepository = theatreRepository;
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
    }

    public Show createShow(String movieId, String theatreId, String screenId, Date start, Date end) {
        Movie movie = movieRepository.get(movieId);
        Theater theater = theatreRepository.get(theatreId);
        Screen screen = screenRepository.get(screenId);
        Show show = new Show(movie, screen, theater, start, end);
        showRepository.save(show);
        return show;
    }

    // get all shows by movie title
    public List<Show> getShowsByMovieTitle(String title) {
        return showRepository.getAll().stream().filter(show -> show.getMovie().getTitle().equalsIgnoreCase(title)).toList();
    }
}
