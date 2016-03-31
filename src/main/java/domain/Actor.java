package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by cathlene on 8/02/2016.
 */
@Entity
public class Actor {

    private String naam;
    private String voornaam;
    private int leeftijd;
   
    @Id
    @GeneratedValue
    private long id;
    private String fullName;

    public  Actor(String voornaam, String naam, int leeftijd){
        this.setNaam(naam);
        this.setVoornaam(voornaam);
        this.setLeeftijd(leeftijd);
        this.setFullName(voornaam, naam);
    }
 public  Actor(String voornaam, String naam, int leeftijd, long id){
        this.setNaam(naam);
        this.setVoornaam(voornaam);
        this.setLeeftijd(leeftijd);
        this.setId(id);
        this.setFullName(voornaam, naam);

    }
    
    public Actor() {
    }


    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if(naam==null || naam.isEmpty()){
            throw new DomainException("geen geldige naam");
        }
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam)
    {       
        if(voornaam==null || voornaam.isEmpty()){
            throw new DomainException("geen geldige voornaam");
        }
        this.voornaam = voornaam;
              
    }
    public int getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(int leeftijd) {
        if(leeftijd<0){
            throw new DomainException("geen geldige leeftijd");
        }
        this.leeftijd = leeftijd;
    }

    public long getId() {
        return id;
    }
    
    public void setFullName(String voornaam, String naam){
        this.fullName=this.getVoornaam()+ " "+ this.getNaam();
    }
    public String getFullName(){
    return fullName;
    }

    public void setId(long id) {
        
        this.id = id;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Actor){
            Actor actor = (Actor) obj;
            if(actor.voornaam.equals(this.voornaam) && actor.naam.equals(this.naam) && actor.leeftijd == this.leeftijd){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}
