/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author cathlene
 */
public class MovieBuilder {

    
    private String title;
    private int duur;
    private String actorVoornaam, actorAchternaam;
    private long id;

   
    
    public MovieBuilder() {
    }
 public MovieBuilder(String title, int duur, String voornaam, String achternaam,long id) {
     this.setActorAchternaam(achternaam);
     this.setActorVoornaam(voornaam);
     this.setDuur(duur);
     this.setTitle(title);
     this.setId(id);
    }

     public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public int getDuur() {
        return duur;
    }

    public String getActorVoornaam() {
        return actorVoornaam;
    }

    public String getActorAchternaam() {
        return actorAchternaam;
    }
   

    
    public void setTitle(String title) {
        if(title==null || title.isEmpty()){
        throw new DomainException("invalid title"); }
        this.title = title;
    }

    public void setDuur(int duur) {
        if(duur<=0){
        throw new DomainException("invalid duration"); }
        this.duur = duur;
    }

    public void setActorAchternaam(String actorAchternaam) {
        if(actorAchternaam==null || actorAchternaam.isEmpty()){
        throw new DomainException("invalid name");        }
        this.actorAchternaam = actorAchternaam;
    }

    public void setActorVoornaam(String actorVoornaam) {
        if(actorVoornaam==null || actorVoornaam.isEmpty()){
        throw new DomainException("invalid firstname"); }
        this.actorVoornaam = actorVoornaam;
    }
    
    public Movie build(Facade facade) {
        if(facade.getActor(actorAchternaam, actorVoornaam)==null){
        throw new DomainException("not valid name");
        }
        Actor hoofdrolspeler = facade.getActor(actorAchternaam, actorVoornaam);
        return new Movie(title, duur, hoofdrolspeler,id);
    }
}
