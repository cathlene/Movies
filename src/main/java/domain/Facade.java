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
