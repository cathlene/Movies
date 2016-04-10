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
    void clearData();

   List<Movie> getAllMovies();
   int getAantalMovies();
    List<Movie> getMoviesWithSpecificDuration(int duur);
    List<Movie> getMoviesWithSpecificActor(Actor actor);

    void deleteMoviesWithSpecificActor(Actor actor);

    public Movie getMovie(String movie);
}

