package db.Actor;

import domain.Actor;
import java.util.List;

/**
 * Created by cathlene on 8/02/2016.
 */
public class ActorRepositorySql implements ActorRepository {


    public void addActor(Actor actor) {

    }

    public void removeActor(Actor actor) {

    }

    public void updateActor(Actor actor) {

    }

    public Actor getActor(String id) {
        return null;
    }

    public int getAantalActors() {
        return 0;
    }

    public List<Actor> getAllActors() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void saveOrUpdate(Actor actor) {
    }

    public Actor getActor(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Actor getActor(String naam, String voornaam) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
