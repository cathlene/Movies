package db.ActorDb;

import domain.Actor;

/**
 * Created by cathlene on 8/02/2016.
 */
public interface ActorRepository {

    public
    void addActor(Actor actor);
    void removeActor(Actor actor);
    void updateActor(Actor actor);

    Actor getActor(String id);
    int getAantalActors();
}
