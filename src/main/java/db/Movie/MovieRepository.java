package db.Movie;

import domain.Actor;
import domain.Movie;

import java.util.List;

/**
 * Created by cathlene on 8/02/2016.
 */
public interface MovieRepository {

    void addMovie(Movie movie);
    void removeMovie(Movie movie);
    void updateMovie(Movie movie);
    Movie getMovie(Movie movie);
    Movie getMovie(long id);

   List<Movie> getAllMovies();
   int getAantalMovies();
    public List<Movie> getMoviesWithSpecificDuration(int duur);
    public List<Movie> getMoviesWithSpecificActor(Actor actor);
}

