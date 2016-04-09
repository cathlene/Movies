package db.Actor;

import domain.Actor;
import java.util.List;

/**
 * Created by cathlene on 8/02/2016.
 */
public interface ActorRepository {

    public
    void addActor(Actor actor);
    void removeActor(Actor actor);
    void updateActor(Actor actor);
    Actor getActor(long id);
    Actor getActor(String naam, String voornaam);
    int getAantalActors();
    List<Actor> getAllActors(); 

    void clearData();
    void removeActor(long id);
}
