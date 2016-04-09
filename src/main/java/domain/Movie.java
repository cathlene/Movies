package domain;

import domain.Actor;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Created by cathlene on 8/02/2016.
 */
@Entity
public class Movie {

    private String title;
    private int duur;
    @ManyToOne
    private Actor hoofdrolSpeler;
    
    @Enumerated(EnumType.STRING)
    private Genre genre;
    
    @Id
    @GeneratedValue
    private long id;
    
    public Movie(){}

    public Movie(String title, int duur, Actor hoofdrolspeler) {
        this.setTitle(title);
        this.setDuur(duur);
        this.setHoofdrolSpeler(hoofdrolspeler);
    }
   
 public Movie(String title, int duur, Actor hoofdrolspeler, long id) {
        this.setTitle(title);
        this.setDuur(duur);
        this.setHoofdrolSpeler(hoofdrolspeler);
        this.setId(id);
    }
    public String getTitle() {
        return title;
    }
public long getId() {
        return id;
    }

 public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public void setId(long id) {
        this.id = id;
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

 @Override
    public boolean equals(Object obj) {
        if (obj instanceof Movie) {
            Movie movie = (Movie) obj;
            return (movie.title.equals(this.title)&& movie.duur==this.duur && movie.hoofdrolSpeler.equals(this.hoofdrolSpeler));
        } else {
            return false;
        }
    }
}
