package db.Actor;

import domain.Actor;
import domain.DomainException;
import db.*;
import domain.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cathlene on 8/02/2016.
 */
public class ActorRepositoryStub implements ActorRepository {
    private static int counter = 0;

    private static Map<Long, Actor> actors;
    
    public ActorRepositoryStub(){

        actors= new HashMap<Long, Actor>();
        Actor actor= new Actor("Johnny", "Depp", 55);
        this.addActor(actor);
         Movie movie=new Movie("PublicEnemies", 118, new Actor("Johnny", "Depp", 55));
       Movie movie2=(new Movie("Rango", 96, new Actor("Johnny", "Depp", 55)));
        actor.addMovie(movie);
       actor.addMovie(movie2);

        
    }
    
     public static int getID(){
        counter++;
        return counter;
    }
     
    public void addActor(Actor actor) {
        if(actor==null){
            throw new DbException("Geen geldige actor");
        }
         actor.setId(ActorRepositoryStub.getID());
        actors.put(actor.getId(),actor);

    }

    public void removeActor(Actor actor) {
        if (actor==null || !actors.containsKey(actor.getId())){
            throw new DbException("Geen geldige actor");
        }
        actors.remove(actor.getId());
    }

    public void updateActor(Actor actor) {
        if(actor==null){
            throw new DbException("Geen geldige actor");
        }
       // actors.put(actor.getId(),actor);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!voornaam "+ actor.getVoornaam());
       actor.setFullName(actor.getVoornaam(), actor.getNaam());
       Actor actorToUpdate= actors.get(actor.getId());
               this.actors.remove(actor.getId());
        actorToUpdate.setFullName(actor.getVoornaam(), actor.getNaam());
        actorToUpdate.setLeeftijd(actor.getLeeftijd());
        actorToUpdate.setNaam(actor.getNaam());
        actorToUpdate.setVoornaam(actor.getVoornaam());
        actors.put(actor.getId(), actorToUpdate);
        System.out.println("nieuwe gegevens  .........."+ actorToUpdate.getNaam()+" "+actorToUpdate.getVoornaam()+" "+actorToUpdate.getFullName());
        this.updateMoviesWithActor(actorToUpdate);
    }
     public void updateMoviesWithActor(Actor actor){
     List<Movie>movies= actor.getMovies();
     for(Movie m:movies){
     m.setHoofdrolSpeler(actor);
     }
    }


    public Actor getActor(long id) {
        if (!actors.containsKey(id)){
            throw new DbException("Geen geldige Id");
        }
        return actors.get(id);
    }

    public int getAantalActors() {
        return actors.size();
    }

    public List<Actor> getAllActors(){
        return new ArrayList<Actor>(actors.values());
    }

   

    public Actor getActor(String naam, String voornaam) {

        if(naam==null || voornaam==null){
        throw new DbException("Geen geldige naam/voornaam");
        }
        List<Actor> actorsLijst = this.getAllActors();

        for(Actor actor: actorsLijst){
            if(actor.getNaam().equals(naam) && actor.getVoornaam().equals(voornaam)){
                return actor;
            }
        }
        return null;
    }

    public void removeActor(long id) {
        actors.remove(id);
    }

    public void clearData() {
               actors= new HashMap<Long, Actor>();
    }
   
}
