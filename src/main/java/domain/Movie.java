package domain;

import domain.Actor;

/**
 * Created by cathlene on 8/02/2016.
 */
public class Movie {

    private String title;
    private int duur;
    private Actor hoofdrolSpeler;

    public Movie(String title, int duur, Actor hoofdrolspeler) {
        this.setTitle(title);
        this.setDuur(duur);
        this.setHoofdrolSpeler(hoofdrolspeler);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title ==null || title.isEmpty() ){
            throw new DomainException("geen geldige titel");
        }
        this.title = title;
    }

    public int getDuur() {
        return duur;
    }

    public void setDuur(int duur) {
        if(duur < 0 ){
            throw new DomainException("geen geldige duur");
        }
        this.duur = duur;
    }

    public Actor getHoofdrolSpeler() {
        return hoofdrolSpeler;
    }

    public void setHoofdrolSpeler(Actor hoofdrolSpeler) {
        if(hoofdrolSpeler==null){
            throw new DomainException("geen geldige hoofdrolspeler");
        }
        this.hoofdrolSpeler = hoofdrolSpeler;
    }


}
