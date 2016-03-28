package domain;
import db.Actor.ActorRepositoryStub;
import db.Actor.ActorRepositorySql;
import db.Actor.DbActorType;
import db.Actor.ActorRepository;

/**
 * Created by cathlene on 15/02/2016.
 */
public class ActorFactory {

    public static ActorRepository createRepository(String repository){
       if(repository.equals(DbActorType.ACTORREPOSITORYSTUB.getType())){
           return new ActorRepositoryStub();
       }else {
           return new ActorRepositorySql();
       }

    }

}

