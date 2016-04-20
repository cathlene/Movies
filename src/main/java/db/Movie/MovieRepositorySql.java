package db.Movie;

import db.DbException;
import domain.Actor;
import domain.Movie;
import java.util.ArrayList;

import java.util.List;
import java.util.ListIterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Created by cathlene on 8/02/2016.
 */
public class MovieRepositorySql implements MovieRepository {

    private EntityManager manager;
    private EntityManagerFactory factory;

    public MovieRepositorySql(String name) {

        factory = Persistence.createEntityManagerFactory(name);
        manager = factory.createEntityManager();
        /*     Actor actor=new Actor("John", "Dep", 55);
        Actor actor2=new Actor("Wil", "Be", 43); 
        this.addMovie(new Movie("Public", 118, actor));
        this.addMovie(new Movie("Into", 120, actor2));*/
    }

    public void closeConnection() {
        try {
            manager.close();
            factory.close();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public boolean alreadyExists(Movie movie) {
        return (this.getMovie(movie.getId()) != null);

    }

    public void addMovie(Movie movie) {
        if (alreadyExists(movie)) {
            throw new DbException("movie already exitsts");
        }
        try {
            manager.getTransaction().begin();
            manager.persist(movie);
            manager.flush();
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public void removeMovie(Movie movie) {
        if (movie == null || !alreadyExists(movie)) {
            throw new DbException("movie does not exixts");
        }
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
        if (movie == null || !alreadyExists(movie)) {
            throw new DbException("movie does not exixts");
        }
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
        if (movie == null || !alreadyExists(movie)) {
            throw new DbException("movie does not exixts");
        }
        try {

            return this.getMovie(movie.getId());
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }

    }

    public Movie getMovie(long id) {

        try {
            return manager.find(Movie.class, id);
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public List<Movie> getAllMovies() {

        try {
            Query query = manager.createQuery("select m from Movie m");
            return query.getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public int getAantalMovies() {

        try {
            Query query = manager.createQuery("select count(m) from Movie m");
            Long aantal = (Long) query.getSingleResult(); // Als je er direct Integer van probeert te maken geeft deze een error: cannot convert Long into Integer
            return aantal.intValue();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public List<Movie> getMoviesWithSpecificDuration(int duur) {
        try {
            Query query = manager.createQuery("select m from Movie m where m.duur <= :duration").setParameter("duration", duur);
            return query.getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public List<Movie> getMoviesWithSpecificActor(Actor actor) {

        try {

            String fullNaam = actor.getFullName();
            int leeftijd = actor.getLeeftijd();
            TypedQuery<Actor> query = manager.createQuery("select m from Actor m where m.fullName = :fullName AND m.leeftijd = :leeftijd", Actor.class);
            query.setParameter("fullName", fullNaam);
            query.setParameter("leeftijd", leeftijd);
            // Actor actor2 = query.getSingleResult();

            return actor.getMovies();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public void clearData() {
        try {
            List<Movie> movies = this.getAllMovies();

            for (Movie m : movies) {
                this.removeMovie(m);
            }

        } catch (Exception e) {

            throw new DbException(e.getMessage(), e);

        }

    }

    public void deleteMoviesWithSpecificActor(Actor actor) {
        try {
            ListIterator<Movie> itr = this.getAllMovies().listIterator();
            while (itr.hasNext()) {
                Movie movie = itr.next();
                Actor actor2 = movie.getHoofdrolSpeler();
                if (actor.equals(actor2)) {
                    this.removeMovie(movie);
                    actor.deleteMovie(movie);
                }
            }
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

}
