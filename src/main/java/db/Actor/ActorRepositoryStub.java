package db.Actor;

import domain.Actor;
import domain.DomainException;
import db.*;

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
        this.addActor(new Actor("Johnny", "Depp", 55));
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
        this.actors.remove(actor.getId());
        actors.put(actor.getId(),actor);
        
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

    public void saveOrUpdate(Actor actor) {
        if (actor==null ){
            throw new DbException("Geen geldige actor");
        }

        if(!actors.containsKey("add"+actor.getId())){
            System.out.print(actor.getId());
            this.addActor(actor);
        }
        else{
            System.out.print("update:"+actor.getId());
            updateActor(actor);
        }
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
   
}
