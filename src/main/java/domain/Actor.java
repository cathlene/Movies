package domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by cathlene on 8/02/2016.
 */
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Actor {

    @NotEmpty(message="Please enter name")
    private String naam;
    
    @NotEmpty(message="Please enter firstname")
    private String voornaam;
    
   //@Min(0)
    @Min(value = 0, message = "Age must be positive")
    private int leeftijd;
    
   @NotNull
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
   
    public void updateMovie(Movie movie) {
        if (movie == null) {
            throw new DomainException("geen geldige film");
        }
        for(int i=0 ;i<movies.size();i++){
            if(movies.get(i).getId()==movie.getId()){
                movies.set(i, movie);
            }
        }
    }
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    
    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;

    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(int leeftijd) {
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
