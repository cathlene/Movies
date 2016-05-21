package domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import domain.Actor;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Movie {

    @NotEmpty(message="Please enter title")
    private String title;

   @Min(value = 0, message = "Duration must be positive")
    private int duur;

    @NotNull(message = "invalid actor")
    @Valid
  //  @ManyToOne(cascade = CascadeType.MERGE)
 //   @JsonBackReference
    private Actor hoofdrolSpeler;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Id
    @GeneratedValue
    private long id;

    public Movie() {
    }

    public Movie(String title, int duur, Actor hoofdrolspeler) {
        this.setTitle(title);
        this.setDuur(duur);
        this.setHoofdrolSpeler(hoofdrolspeler);
    }

    public Movie(String title, int duur, Actor hoofdrolspeler, long id) {
        this(title, duur, hoofdrolspeler);
        this.setId(id);
    }

   // @NotNull
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
        this.title = title;
    }

    
    public int getDuur() {
        return duur;
    }

    public void setDuur(int duur) {
        this.duur = duur;
    }

    public Actor getHoofdrolSpeler() {
        return hoofdrolSpeler;
    }

    public void setHoofdrolSpeler(Actor hoofdrolSpeler) {
        this.hoofdrolSpeler = hoofdrolSpeler;
    }

 /*   @Override
    public boolean equals(Object obj) {
        if (obj instanceof Movie) {
            Movie movie = (Movie) obj;
            return (movie.title.equals(this.title) && movie.duur == this.duur && movie.hoofdrolSpeler.equals(this.hoofdrolSpeler));
        } else {
            return false;
        }
    }*/

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 83 * hash + this.duur;
        hash = 83 * hash + (this.hoofdrolSpeler != null ? this.hoofdrolSpeler.hashCode() : 0);
        hash = 83 * hash + (this.genre != null ? this.genre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movie other = (Movie) obj;
        if (this.duur != other.duur) {
            return false;
        }
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            return false;
        }
        if (this.hoofdrolSpeler != other.hoofdrolSpeler && (this.hoofdrolSpeler == null || !this.hoofdrolSpeler.equals(other.hoofdrolSpeler))) {
            return false;
        }
        if (this.genre != other.genre) {
            return false;
        }
        return true;
    }
    
}
