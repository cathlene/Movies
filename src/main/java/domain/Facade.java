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
     public void removeActor(long id){
        actorService.removeActor(id);
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
    public void clearMovieData(){
    movieService.clearData();
    }
    public void clearActorData(){
    actorService.clearData();
    }
    public int getAantalActors(){
    return actorService.getAantalActors();
    }
    public int getAantalMovies(){
    return movieService.getAantalMovies();
    }
    public MovieService getMovieRepository(){
        return this.movieService;
    }
    public ActorService getActorRepository(){
        return this.actorService;
    }

    public Movie getMovie(Movie movie){
        return movieService.getMovie(movie);
    }
     public Movie getMovie(long id){
        return movieService.getMovie(id);
    }
    public Actor getActor(String naam, String voornaam){
        return actorService.getActor(naam,voornaam);
    }
     public Actor getActor(long id){
        return actorService.getActor(id);
    }

    public List<Movie> getMoviesWithSpecificActor(Actor actor){
        return movieService.getMoviesWithSpecificActor(actor);
    }
    public void deleteMoviesWithSpeceficActor(Actor actor){
        movieService.deleteMoviesWithSpecificActor(actor);
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

  

}
