package domain;

import db.Movie.MovieRepositoryStub;
import db.Movie.DbMovieType;
import db.Movie.MovieRepository;
import db.Movie.MovieRepositorySql;

/**
 * Created by cathlene on 15/02/2016.
 */
public class MovieFactory {

    public static MovieRepository createRepository(String repository){
       if(repository.equals(DbMovieType.MOVIEREPOSITORYSTUB.getType())){
           return new MovieRepositoryStub();
       }
       else{
       return new MovieRepositorySql();
       }

    }
}
