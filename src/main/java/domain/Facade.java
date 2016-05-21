package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.client.RestTemplate;
import restDomain.Result;
import service.ActorService;
import service.MovieService;

/**
 * Created by cathlene on 8/02/2016.
 */
public class Facade {

    private ActorService actorService;
    private MovieService movieService;

    public Facade(String repository) {

        actorService = new ActorService(repository);
        movieService = new MovieService(repository);

    }

    public void addMovie(Movie movie) {
        if (movie == null) {
            throw new DomainException("invalid movie");
        }
        movieService.addMovie(movie);
        Actor actor = movie.getHoofdrolSpeler();
        actor.addMovie(movie);
        actorService.updateActor(actor);

    }

    public void addActor(Actor actor) {
        actorService.addActor(actor);
    }

    public void removeActor(Actor actor) {
        if (actor == null) {
            throw new DomainException("invalid actor");
        }
        this.deleteMoviesWithSpeceficActor(actor);
        actorService.removeActor(actor);
    }

    public void removeActor(long id) {
        if (this.getActor(id) == null) {
            throw new DomainException("invalid actor");
        }
        this.deleteMoviesWithSpeceficActor(this.getActor(id));
        actorService.removeActor(id);
    }

    public void updateMovie(Movie movie) {
        movieService.updateMovie(movie);
       //   Actor actor = movie.getHoofdrolSpeler();
       // actor.updateMovie(movie);
       // actorService.updateActor(actor);
        
    }

    public void updateActor(Actor actor) {
        actorService.updateActor(actor);
    }

    public void removeMovie(Movie movie) {
        if (movie == null) {
            throw new DomainException("invalid movie");
        }
        movieService.removeMovie(movie);
        Actor actor = movie.getHoofdrolSpeler();
        actor.deleteMovie(movie);
        actorService.updateActor(actor);
    }

    public void clearMovieData() {
        movieService.clearData();
    }

    public void clearActorData() {
        actorService.clearData();
    }

    public void closeConnection() {
        movieService.closeConnection();
        actorService.closeConnection();
    }

    public int getAantalActors() {
        return actorService.getAantalActors();
    }

    public int getAantalMovies() {
        return movieService.getAantalMovies();
    }

    public MovieService getMovieRepository() {
        return this.movieService;
    }

    public ActorService getActorRepository() {
        return this.actorService;
    }

    public Movie getMovie(Movie movie) {
        return movieService.getMovie(movie);
    }

    public Movie getMovie(long id) {
        return movieService.getMovie(id);
    }

    public Actor getActor(String naam, String voornaam) {
        return actorService.getActor(naam, voornaam);
    }

    public Actor getActor(long id) {
        return actorService.getActor(id);
    }

    public List<Movie> getMoviesWithSpecificActor(Actor actor) {
        return movieService.getMoviesWithSpecificActor(actor);
    }

    public void deleteMoviesWithSpeceficActor(Actor actor) {
        movieService.deleteMoviesWithSpecificActor(actor);
    }

    public List<Movie> getMoviesWithSpecificDuration(int duur) {
        return movieService.getMoviesWithSpecificDuration(duur);
    }

    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    public List<Actor> getActors() {
        return actorService.getActors();
    }

    public Result rating(String title) {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("t", title);
        RestTemplate restTemplate = new RestTemplate();

        Result response = restTemplate.getForObject("http://www.omdbapi.com/?t={t}&y=&plot=short&r=json", Result.class, parameters);
        return response;
    }

}
