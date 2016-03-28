package domain;


import java.util.List;
import service.ActorService;
import service.MovieService;

/**
 * Created by cathlene on 8/02/2016.
 */
public class Facade {

   private ActorService actorService;
    private MovieService movieService;
    public Facade(String repository){

      actorService= new ActorService(repository);
      movieService= new MovieService(repository);
        
    }

    public void addMovie(Movie movie){
        movieService.addMovie(movie);
    }
    public void addActor(Actor actor){
        actorService.addActor(actor);
    }
    public void removeActor(Actor actor){
        actorService.removeActor(actor);
    }
    public void updateMovie(Movie movie){
        movieService.updateMovie(movie);
    }
    public void updateActor(Actor actor){
        actorService.updateActor(actor);
    }
    public void removeMovie(Movie movie){
        movieService.removeMovie(movie);
    }
    public MovieService getMovieRepository(){
        return this.movieService;
    }
    public ActorService getActorRepository(){
        return this.actorService;
    }

    public Movie getMovie(String title){
        return movieService.getMovie(title);
    }
    public Actor getActor(String id){
        return actorService.getActor(id);
    }
    public List<Movie> getMoviesWithSpecificActor(Actor actor){
        return movieService.getMoviesWithSpecificActor(actor);
    }
    public List<Movie> getMoviesWithSpecificDuration(int duur){
        return movieService.getMoviesWithSpecificDuration(duur);
    }

    public List<Movie>getMovies(){
    return movieService.getMovies();
    }

    public List<Actor> getActors() {
        return actorService.getActors();
    }

    public void saveOrUpdate(Actor actor) {
         actorService.saveOrUpdate(actor);
    }

}
