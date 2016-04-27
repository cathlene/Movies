package domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by cathlene on 8/02/2016.
 */
@Entity
public class Actor {

   // @NotNull(message="invalid name")
    private String naam;
    
   // @NotNull(message="invalid firstname")
    private String voornaam;
    
   // @Min(0)
    private int leeftijd;
    
      // @Valid
    //@NotNull
   @OneToMany(mappedBy = "hoofdrolSpeler")
    private List<Movie> movies;

    @Id
    @GeneratedValue
    private long id;
    
    
    private String fullName;

    public Actor(String voornaam, String naam, int leeftijd) {
        this.setNaam(naam);
        this.setVoornaam(voornaam);
        this.setLeeftijd(leeftijd);
        movies = new ArrayList<Movie>();
    }

    public Actor(String voornaam, String naam, int leeftijd, long id) {
        this(voornaam, naam, leeftijd);
        this.setId(id);
        movies = new ArrayList<Movie>();

    }

    public Actor() {
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void addMovie(Movie movie) {
        if (movie == null) {
            throw new DomainException("geen geldige film");
        }
        if (this.getMovies() == null) {
            movies = new ArrayList<Movie>();
        }

        movies.add(movie);
        movie.setHoofdrolSpeler(this);
    }

    public void deleteMovie(Movie movie) {
        if (movie == null) {
            throw new DomainException("geen geldige film");
        }
        movies.remove(movie);
        movie.setHoofdrolSpeler(null);
    }
    //@NotNull
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.isEmpty()) {
            throw new DomainException("geen geldige naam");
        }
        this.naam = naam;
    }

   // @NotNull
    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        if (voornaam == null || voornaam.isEmpty()) {
            throw new DomainException("geen geldige voornaam");
        }
        this.voornaam = voornaam;

    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(int leeftijd) {
        if (leeftijd < 0) {
            throw new DomainException("geen geldige leeftijd");
        }
        this.leeftijd = leeftijd;
    }

    public long getId() {
        return id;
    }

   
    public String getFullName() {
        return this.getVoornaam() + " " + this.getNaam();
    }

    public void setId(long id) {

        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Actor) {
            Actor actor = (Actor) obj;
            return (actor.voornaam.equals(this.voornaam) && actor.naam.equals(this.naam) && actor.leeftijd == this.leeftijd);

        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.naam != null ? this.naam.hashCode() : 0);
        hash = 53 * hash + (this.voornaam != null ? this.voornaam.hashCode() : 0);
        hash = 53 * hash + this.leeftijd;
        return hash;
    }

    @Override
    public String toString() {
        return this.getFullName() + " " + this.getLeeftijd() + " " + this.getId();
    }
}
