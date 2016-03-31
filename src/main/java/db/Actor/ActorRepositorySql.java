package db.Actor;

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
public class ActorRepositorySql implements ActorRepository {

    private EntityManager manager;
    private EntityManagerFactory factory;
    public ActorRepositorySql(String name){
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

    public void addActor(Actor actor) {
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
            Query query= manager.createQuery("select count(m) from Actor m");
            return (Integer) query.getSingleResult();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public List<Actor> getAllActors() {

        try {
            Query query= manager.createQuery("select m from Actor m");
            return query.getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    

    public Actor getActor(long id) {
 try {
            Query query= manager.createQuery("select m from Actor m where m.name like :actorId").setParameter("actorId", id);
             Actor actor = (Actor) query.getSingleResult();
            return actor;
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public Actor getActor(String naam, String voornaam) {
         try {
            Query query= manager.createQuery("select m from Actor m where m.name= :naam AND m.voornaam = :voornaam");
             Actor actor = (Actor) query.getSingleResult();
            return actor;
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public void removeActor(long id) {
        Actor actor= this.getActor(id);
        this.removeActor(actor);
    }
}
