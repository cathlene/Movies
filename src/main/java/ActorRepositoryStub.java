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
        if (actor.getId()==null || actor.getId().isEmpty() ){
            throw new IllegalArgumentException("Geen geldige actor");
        }
        actors.put(actor.getId(),actor);

    }

    public void removeActor(Actor actor) {
        if (actor.getId()==null || actor.getId().isEmpty() || !actors.containsKey(actor.getId())){
            throw new IllegalArgumentException("Geen geldige actor");
        }
        actors.remove(actor.getId());
    }

    public void updateActor(Actor actor) {
        if (actor.getId()==null || actor.getId().isEmpty() || !actors.containsKey(actor.getId())){
            throw new IllegalArgumentException("Geen geldige actor");
        }
        actors.put(actor.getId(),actor);

    }


    public Actor getActor(String id) {
        if (id==null || id.isEmpty() || !actors.containsKey(id)){
            throw new IllegalArgumentException("Geen geldige Id");
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
