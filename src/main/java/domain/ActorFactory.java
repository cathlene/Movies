package domain;
import db.Actor.ActorRepositoryStub;
import db.Actor.ActorRepositorySql;
import db.Actor.DbActorType;
import db.Actor.ActorRepository;

/**
 * Created by cathlene on 15/02/2016.
 */
public class ActorFactory {

    public static ActorRepository createRepository(DbActorType type){
        switch (type){
            case ACTORREPOSITORYSTUB:
                return new ActorRepositoryStub();
            case ACTORREPOSITORYSQL:
                return new ActorRepositorySql();
            default:
                throw new DomainException("invalid type");
        }

    }

}

