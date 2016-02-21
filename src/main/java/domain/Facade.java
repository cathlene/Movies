package domain;

import db.ActorDb.ActorRepository;
import db.ActorDb.ActorRepositoryStub;
import db.ActorDb.DbActorType;
import db.MovieDb.DbMovieType;
import db.MovieDb.MovieRepository;

import java.util.List;

/**
 * Created by cathlene on 8/02/2016.
 */
public class Facade {

    private ActorRepository actorRepository;
    private MovieRepository movieRepository;
    public Facade(){

        actorRepository= ActorFactory.createRepository(DbActorType.ACTORREPOSITORYSTUB);
        movieRepository= MovieFactory.createRepository(DbMovieType.MOVIEREPOSITORYSTUB);
    }

    public void addMovie(Movie movie){
        movieRepository.addMovie(movie);
    }
    public void addActor(Actor actor){
        actorRepository.addActor(actor);
    }
    public void removeActor(Actor actor){
        actorRepository.removeActor(actor);
    }
    public void updateMovie(Movie movie){
        movieRepository.updateMovie(movie);
    }
    public void updateActor(Actor actor){
        actorRepository.updateActor(actor);
    }
    public void removeMovie(Movie movie){
        movieRepository.removeMovie(movie);
    }
    public MovieRepository getMovieRepository(){
        return this.movieRepository;
    }
    public ActorRepository getActorRepository(){
        return this.actorRepository;
    }

    public Movie getMovie(String title){
        return movieRepository.getMovie(title);
    }
    public Actor getActor(String id){
        return actorRepository.getActor(id);
    }
    public List<Movie> getMoviesWithSpecificActor(Actor actor){
        return movieRepository.getMoviesWithSpecificActor(actor);
    }
    public List<Movie> getMoviesWithSpecificDuration(int duur){
        return movieRepository.getMoviesWithSpecificDuration(duur);
    }


}
