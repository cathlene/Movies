package db.Actor;

import db.DbException;
import domain.Actor;
import domain.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Created by cathlene on 8/02/2016.
 */
public class ActorRepositorySql implements ActorRepository {

    private EntityManager manager;
    private EntityManagerFactory factory;

    public ActorRepositorySql(String name) {
        factory = Persistence.createEntityManagerFactory(name);
        manager = factory.createEntityManager();
        /*Actor actor=new Actor("John", "De", 55);
        Actor actor2=new Actor("Wil", "Be", 43);
        this.addActor(actor);
        this.addActor(actor2);
       Movie movie = new Movie("Public", 118, actor);
        Movie movie2 = new Movie("Into", 120, actor2); */

    }

    public void closeConnection() {
        try {
            manager.close();
            factory.close();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public boolean alreadyExists(Actor actor) {

       for(Actor actor1: this.getAllActors()){
           if(actor1.equals(actor)) {
               return true;
           }
       }
       return false;
    }

    public void addActor(Actor actor) {
        if (actor == null || alreadyExists(actor)) {
            throw new DbException("Actor already exists");
        }
        try {
            manager.getTransaction().begin();
            manager.persist(actor);
            manager.flush();
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public void removeActor(Actor actor) {
        if (actor == null || !alreadyExists(actor)) {
            throw new DbException("actor does not exists");
        }
        try {
            manager.getTransaction().begin();
            manager.remove(actor);
            manager.flush();
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }

    }

    public void updateActor(Actor actor) {
        if (actor == null) {
            throw new DbException("actor does not exists");
        }
        try {

            manager.getTransaction().begin();
            manager.merge(actor);
            manager.flush();
            manager.getTransaction().commit();

        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public int getAantalActors() {
        try {
            Query query = manager.createQuery("select count(m) from Actor m");
            Long aantal = (Long) query.getSingleResult(); // Als je er direct Integer van probeert te maken geeft deze een error: cannot convert Long into Integer
            return aantal.intValue();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public List<Actor> getAllActors() {

        try {
            Query query = manager.createQuery("select m from Actor m");
            return query.getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public Actor getActor(long id) {
        try {
            return manager.find(Actor.class, id);
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public Actor getActor(String naam, String voornaam) {
        try {
            TypedQuery<Actor> query = manager.createQuery("select m from Actor m where m.naam= :naam AND m.voornaam = :voornaam", Actor.class);
            query.setParameter("voornaam", voornaam);
            query.setParameter("naam", naam);
            return query.getSingleResult();

        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public void removeActor(long id) {
        Actor actor = this.getActor(id);
        this.removeActor(actor);
    }

    public void clearData() {
        try {

            List<Actor> actors = this.getAllActors();
            for (Actor actor : actors) {
                this.removeActor(actor);

            }
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }
}
