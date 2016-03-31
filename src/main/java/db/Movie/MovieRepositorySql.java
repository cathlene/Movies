package db.Movie;

import db.DbException;
import domain.Actor;
import domain.Movie;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Created by cathlene on 8/02/2016.
 */
public class MovieRepositorySql implements MovieRepository {

    private EntityManager manager;
    private EntityManagerFactory factory;

    public MovieRepositorySql(String name) {

        factory = Persistence.createEntityManagerFactory(name);
        manager = factory.createEntityManager();
    }

    public void closeConnection() {
        try {
            manager.close();
            factory.close();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public void addMovie(Movie movie) {
        try {
            manager.getTransaction().begin();
            manager.persist(movie);
            manager.flush(); // wordt onmiddelijk op db gezet en niet enkel in geheugen
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public void removeMovie(Movie movie) {
         try {
            manager.getTransaction().begin();
            manager.remove(movie);
            manager.flush(); // wordt onmiddelijk op db gezet en niet enkel in geheugen
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public void updateMovie(Movie movie) {

        try {
            manager.getTransaction().begin();
            manager.merge(movie);
            manager.flush(); 
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public Movie getMovie(Movie movie) {

        try {
            
            return this.getMovie(movie.getId());
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
       
    }

    public Movie getMovie(long id) {

        try {
            Query query= manager.createQuery("select m from Movies m where m.name like :movieId").setParameter("movieId", id);
             Movie movie = (Movie) query.getSingleResult();
            return movie;
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public List<Movie> getAllMovies() {

        try {
            Query query= manager.createQuery("select m from Movies m");
            return query.getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public int getAantalMovies() {

        try {
            Query query= manager.createQuery("select count(m) from Movies m");
            return (Integer) query.getSingleResult();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public List<Movie> getMoviesWithSpecificDuration(int duur) {
        try {
            Query query= manager.createQuery("select m from Movies m where m.duur <= duration").setParameter("duration", duur);
            return query.getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public List<Movie> getMoviesWithSpecificActor(Actor actor) {
        try {
            long id= actor.getId();
            Query query= manager.createQuery("select m from Movies m where m.id like :movieId").setParameter("movieId", id);
            return query.getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

}
