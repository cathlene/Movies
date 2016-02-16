package domain;

import db.MovieDb.*;

/**
 * Created by cathlene on 15/02/2016.
 */
public class MovieFactory {

    public static MovieRepository createRepository(DbMovieType type){
        switch (type){
            case MOVIEREPOSITORYSTUB:
                return new MovieRepositoryStub();
            case MOVIEREPOSITORYSQL:
                return new MovieRepositorySql();
            default:
                throw new DomainException("invalid type");
        }

    }
}
