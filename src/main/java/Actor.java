/**
 * Created by cathlene on 8/02/2016.
 */
public class Actor {

    private String naam;
    private String voornaam;
    private int leeftijd;
    private String id;

    public  Actor(String naam, String Voornaam, int leeftijd, String id){
        this.setNaam(naam);
        this.setVoornaam(voornaam);
        this.setLeeftijd(leeftijd);
        this.setId(id);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if(naam==null || naam.isEmpty()){
            throw new IllegalArgumentException("geen geldige naam");
        }
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam)
    {
        if(voornaam==null || voornaam.isEmpty()){
            throw new IllegalArgumentException("geen geldige voornaam");
        }
        this.voornaam = voornaam;
    }
    public int getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(int leeftijd) {
        if(leeftijd<0){
            throw new IllegalArgumentException("geen geldige leeftijd");
        }
        this.leeftijd = leeftijd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id==null || id.isEmpty()){
            throw new IllegalArgumentException("geen geldige id");
        }
        this.id = id;
    }

}
