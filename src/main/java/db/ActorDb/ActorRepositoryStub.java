package db.ActorDb;

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

    private Map<String, Actor> actors;
    public ActorRepositoryStub(){

        actors= new HashMap<String, Actor>();
        this.addActor(new Actor("Johnny", "Depp", 55,"John"));
    }
    public void addActor(Actor actor) {
        if (actor==null ){
            throw new DbException("Geen geldige actor");
        }
        actors.put(actor.getId(),actor);

    }

    public void removeActor(Actor actor) {
        if (!actors.containsKey(actor.getId())){
            throw new DbException("Geen geldige actor");
        }
        actors.remove(actor.getId());
    }

    public void updateActor(Actor actor) {
        if (!actors.containsKey(actor.getId())){
            throw new DbException("Geen geldige actor");
        }
        actors.put(actor.getId(),actor);

    }


    public Actor getActor(String id) {
        if (id==null || id.isEmpty() || !actors.containsKey(id)){
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
}
