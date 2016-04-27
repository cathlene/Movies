package domain;

import com.sun.javafx.geom.AreaOp;
import db.Actor.ActorRepository;
import db.Actor.ActorRepositoryStub;
import db.DbException;
import domain.*;
import java.time.Clock;

import org.junit.Test;

import java.util.List;
import org.junit.After;

import static org.junit.Assert.*;

/**
 * Created by cathlene on 14/02/2016.
 */
public class DbTest {

    private Actor actor, actor2;
    private Movie movie, movie2;
    private Facade facade;

    @org.junit.Before
    public void setUp() {
        facade = new Facade("sql");  
        this.clearDataMovies();
        this.clearDataActor();


        actor = new Actor("Johnny", "Depp", 55);
        actor2 = new Actor("Willie", "Bend", 43);

        movie = new Movie("PublicEnemies", 118, actor);
        movie2 = new Movie("Into the wild", 120, actor2);
        facade.addActor(actor);
        facade.addActor(actor2);
        facade.addMovie(movie);
        facade.addMovie(movie2);
      

    }

    @Test
    public void testGetActor_met_correcte_naam_voornaam() {
       assertEquals(actor2.getId(), facade.getActor(actor2.getNaam(), actor2.getVoornaam()).getId());

    }

    @Test
    public void testRemoveActor_met_correcte_voornaam_naam_id_leeftijd_verwijdert_actor() {
        assertEquals(2, facade.getAantalActors());
        facade.removeActor(actor);
        assertEquals(1, facade.getAantalActors());

    }

    @Test
    public void testUpdateActor_met_correcte_voornaam_naam_id_leeftijd_update_actor_en_update_film_met_hoofdrolspeler() {
        Movie movieNew= new Movie("Mobie",45,actor);
        facade.addMovie(movieNew);
        assertEquals(actor.getLeeftijd(), facade.getActor("Depp", "Johnny").getLeeftijd());
        Movie movie= facade.getMoviesWithSpecificActor(actor).get(0);
        Movie movie2= facade.getMoviesWithSpecificActor(actor).get(1);

        Actor nieuwActor=new Actor("Johnny", "Depp", 56, actor.getId());
        facade.updateActor(nieuwActor);
        assertEquals(56, facade.getActor("Depp", "Johnny").getLeeftijd());
        assertEquals(56, movie.getHoofdrolSpeler().getLeeftijd());
        assertEquals(56, movie2.getHoofdrolSpeler().getLeeftijd());

        
    }
    @Test
    public void testUpdateEnDeleteMovie_delete_ook_movie_bij_actor() {
        Actor a = new Actor("a", "a", 4); 
        facade.addActor(a); // add actor  aan facade
        Movie movieNew= new Movie("hjk",78,a);
        facade.addMovie(movieNew); // add movie aan facade
        movieNew = facade.getMovie(movieNew.getId());
      //  a.addMovie(movieNew); // voegt movie toe aan actor
        

        movieNew.setTitle("rhe"); //update movie

        facade.updateMovie(movieNew); // update movie
        facade.updateActor(actor);
        
        List<Movie> movell= movieNew.getHoofdrolSpeler().getMovies(); 

        facade.removeMovie(movieNew);  
       // movieNew.getHoofdrolSpeler().getMovies().remove(movieNew);   
        a = facade.getActor(a.getId());
        Movie movieNew2 = new Movie("hjk2",21,a);  
        facade.addMovie(movieNew2);   // add nieuwe movie
      //  a.addMovie(movieNew2);         // add movie bij acteur
        a.setNaam("jo");
         facade.updateMovie(movieNew2); // update movie
        facade.updateActor(actor);
        
        List<Movie> movies = facade.getMovies();
        

        
    }
    
     @Test
    public void testUpdateActor_wanneer_naam_wordt_geupdated_met_correcte_voornaam_naam_id_leeftijd_update_voornaam_van_actor_en_update_film_met_hoofdrolspeler() {
        Movie movieNew= new Movie("Mobie",45,actor);
        facade.addMovie(movieNew);
        assertEquals(actor.getLeeftijd(), facade.getActor("Depp", "Johnny").getLeeftijd());
        Movie movie= facade.getMoviesWithSpecificActor(actor).get(0);
        Movie movie2= facade.getMoviesWithSpecificActor(actor).get(1);

        Actor nieuwActor=new Actor("Johnnies", "Depp", 55, actor.getId());
        facade.updateActor(nieuwActor);
        assertEquals("Johnnies", facade.getActor(actor.getId()).getVoornaam());
        assertEquals("Johnnies", movie.getHoofdrolSpeler().getVoornaam());
        assertEquals("Johnnies", movie2.getHoofdrolSpeler().getVoornaam());

        
    }

    @Test
    public void testUpdateActor_wanneer_naam_wordt_geupdated_met_correcte_voornaam_naam_id_leeftijd_update_fullname_van_actor_en_update_film_met_hoofdrolspeler() {
        Movie movieNew= new Movie("Mobie",45,actor);
        facade.addMovie(movieNew);
        assertEquals(actor.getLeeftijd(), facade.getActor("Depp", "Johnny").getLeeftijd());
        Movie movie= facade.getMoviesWithSpecificActor(actor).get(0);
        Movie movie2= facade.getMoviesWithSpecificActor(actor).get(1);

        Actor nieuwActor=new Actor("Johnnies", "Depp", 55, actor.getId());
        facade.updateActor(nieuwActor);
        assertEquals("Johnnies Depp", facade.getActor(actor.getId()).getFullName());
        assertEquals("Johnnies Depp", movie.getHoofdrolSpeler().getFullName());
        assertEquals("Johnnies Depp", movie2.getHoofdrolSpeler().getFullName());

        
    }
    @Test
    public void testUpdateMovie_met_correcte_titel_duur_hoofdrolspeler_update_movie_en_update_bijhorende_film_van_bijhorende_actor() {
        assertEquals(120, facade.getMovie(movie2).getDuur());
        movie2.setDuur(110);
        facade.updateMovie(movie2);
        assertEquals(110, facade.getMovie(movie2).getDuur());
         facade.getMoviesWithSpecificActor(actor2);
       assertEquals(110, facade.getMoviesWithSpecificActor(movie2.getHoofdrolSpeler()).get(0).getDuur());
       //        assertEquals(110, facade.getMoviesWithSpecificActor(actor2).get(0).getDuur()); is niet geupdated

    }
  


    @Test
    public void testRemoveMovie_met_correcte_titel_duur_hoofdrolspeler_verwijdert_movie() {
        assertEquals(2, facade.getAantalMovies());
        facade.removeMovie(movie);
        assertEquals(1, facade.getAantalMovies());

    }

    @Test(expected = DbException.class)
    public void testRemoveMovie_geeft_DbExcecption_wanneer_movie_met_bepaalde_titel_niet_bestaat() {
        facade.removeMovie(new Movie("Ranbo", 96, new Actor("Johnny", "Depp", 55, 20)));
        assertEquals(1, facade.getAantalMovies());

    }

    @Test(expected = DomainException.class)
    public void testRemoveMovie_met_null_waarden_geeft_DbExcecption() {
        facade.removeMovie(null);
        assertEquals(1, facade.getAantalMovies());

    }

    @Test(expected = DbException.class)
    public void testRemoveActor_geeft_DbExcecption_wanneer_de_acteur_met_id_niet_bestaat() {
        facade.removeActor(new Actor("Johnny", "Depp", 55, 13));
        assertEquals(1, facade.getAantalMovies());

    }

    @Test(expected = DomainException.class)
    public void testRemoveActor_met_null_waarden_geeft_DbExcecption() {
        facade.removeActor(null);
        assertEquals(1, facade.getAantalMovies());

    }

    @Test
    public void testGetMovie_met_correcte_title_duur_hoofdrolSpeler() {
        assertEquals(movie, facade.getMovie(movie));

    }

    @Test
    public void testAdd_movie_met_correcte_voornaam_naam_id_leeftijd_voegt_movie_toe() {
        Movie movieBum = new Movie("bum", 78, actor2);
        facade.addMovie(movieBum);

        assertEquals(3, facade.getAantalMovies());
    }

    @Test
    public void testAddActor_met_correcte_voornaam_naam_id_leeftijd_voegt_actor_toe() {

        ActorRepository actorRepository = new ActorRepositoryStub();
        actorRepository.addActor(actor);
        assertEquals(2, actorRepository.getAantalActors());
    }

    @Test
    public void testGetMoviesWithSPecificActor_geeft_lijst_van_films_terug_met_de_specifieke_geldige_acteur() {
        Movie movieThriller = new Movie("head", 98, actor);
        facade.addMovie(movieThriller);
        List<Movie> movies = facade.getMoviesWithSpecificActor(actor);
        assertEquals("PublicEnemies", movies.get(0).getTitle());
        assertEquals("head", movies.get(1).getTitle());
        assertEquals(2, movies.size());
    }

 

    @Test
    public void testGetMoviesWithSPecificDuration_geeft_lijst_van_films_terug_met_de_specifieke_juiste_duur_limiet() {
        List<Movie> movies = facade.getMoviesWithSpecificDuration(120);
        assertEquals("PublicEnemies", movies.get(0).getTitle());
        assertEquals("Into the wild", movies.get(1).getTitle());
    }

    @Test
    public void testGetMoviesWithSPecificDuration_geeft_lege_lijst_van_films_terug_met_de_specifieke_foute_duur_limiet() {
        List<Movie> movies = facade.getMoviesWithSpecificDuration(80);
        assertTrue(movies.isEmpty());
    }

    @Test
    public void testGetMoviesWithSPecificDuration_geeft__lijst_van_films_terug_met_de_specifieke__duur_limiet() {
        List<Movie> movies = facade.getMoviesWithSpecificDuration(119);
        assertEquals(1, movies.size());
    }

    @Test
    public void testGetMovies_returns_list_with_all_Movies() {
        List<Movie> movies = facade.getMovies();
        assertEquals(2, movies.size());
    }
    
    
    public void clearDataMovies() {
        try {
           List<Movie> movies = facade.getMovies();

            for (Movie m : movies) {
                facade.removeMovie(m);
            }
                      

        } catch (Exception e) {

            throw new DbException(e.getMessage(), e);

        }

    }
   
    public void clearDataActor() {
        try {

            List<Actor> actors = facade.getActors();
            for (Actor actor : actors) {
                facade.removeActor(actor);

            }
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

}
