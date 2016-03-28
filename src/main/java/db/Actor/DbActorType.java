package db.Actor;

/**
 * Created by cathlene on 15/02/2016.
 */
public enum DbActorType {

    ACTORREPOSITORYSQL("sql"),
    ACTORREPOSITORYSTUB("stub");


    private String type;
    private DbActorType(String type){
        this.type=type;
    }

    public String getType(){
        return this.type;
    }
}
