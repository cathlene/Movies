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

    }

    public void closeConnection() {
        try {
            factory.close();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

     public void closeManager(){
       try{
           manager.close();
        }
        catch(Exception e){
            throw new DbException(e.getMessage(),e);
        }
    }
     
       public void openConnection(){
        try {
            manager = factory.createEntityManager();
        } 
        catch(Exception e){
            throw new DbException(e.getMessage(),e);
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
            this.openConnection();
            manager.getTransaction().begin();
            manager.persist(actor);
            manager.flush();
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
        finally{
            this.closeManager();
        }
    }

    public void removeActor(Actor actor) {
        if (actor == null || !alreadyExists(actor)) {
            throw new DbException("actor does not exists");
        }
        try {
            this.openConnection();
            manager.getTransaction().begin();
            if (!manager.contains(actor)) {
            actor = manager.merge(actor);
                }
            manager.remove(actor);
            manager.flush();
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
        finally{
            this.closeManager();
        }

    }

    public void updateActor(Actor actor) {
        if (actor == null) {
            throw new DbException("actor does not exists");
        }
        try {
            this.openConnection();
            manager.getTransaction().begin();
            manager.merge(actor);
            manager.flush();
            manager.getTransaction().commit();

        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
        finally{
            this.closeManager();
        }
    }

    public int getAantalActors() {
        try {
            this.openConnection();
            Query query = manager.createQuery("select count(m) from Actor m");
            Long aantal = (Long) query.getSingleResult(); // Als je er direct Integer van probeert te maken geeft deze een error: cannot convert Long into Integer
            return aantal.intValue();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
        finally{
            this.closeManager();
        }
    }

    public List<Actor> getAllActors() {

        try {
            this.openConnection();
            Query query = manager.createQuery("select m from Actor m");
            return query.getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
        finally{
            this.closeManager();
        }
    }

    public Actor getActor(long id) {
        try {
            this.openConnection();
            return manager.find(Actor.class, id);
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
        finally{
            this.closeManager();
        }
    }

    public Actor getActor(String naam, String voornaam) {
        try {
            this.openConnection();
            TypedQuery<Actor> query = manager.createQuery("select m from Actor m where m.naam= :naam AND m.voornaam = :voornaam", Actor.class);
            query.setParameter("voornaam", voornaam);
            query.setParameter("naam", naam);
            return query.getSingleResult();

        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
        finally{
            this.closeManager();
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
