/**
 * Created by cathlene on 14/02/2016.
 */
public class Maintje {
    public static void main (String[] args){

        Facade facade = new Facade();
        System.out.print(facade.getMovie("PublicEnemy").getTitle());
    }
}
