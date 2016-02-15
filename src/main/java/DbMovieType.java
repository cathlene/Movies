/**
 * Created by cathlene on 15/02/2016.
 */
public enum DbMovieType {

    MOVIEREPOSITORYSQL("movieSql"),
    MOVIEREPOSITORYSTUB("movieStub");


    private String type;
    private DbMovieType(String type){
        this.type=type;
    }

    public String getType(){
        return this.type;
    }
}
