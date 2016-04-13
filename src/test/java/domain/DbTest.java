package domain;

import com.sun.javafx.geom.AreaOp;
import db.Actor.ActorRepository;
import db.Actor.ActorRepositoryStub;
import db.DbException;
import domain.*;
import java.time.Clock;

import org.junit.Test;

import java.util.List;

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
        facade.clearMovieData();
       facade.clearActorData();
        

        actor = new Actor("Johnny", "Depp", 55);
        actor2 = new Actor("Willie", "Bend", 43);

        movie = new Movie("PublicEnemies", 118, actor);
        movie2 = new Movie("Into the wild", 120, actor2);
        facade.addActor(actor);
        facade.addActor(actor2);
        facade.addMovie(movie);
        facade.addMovie(movie2);
        actor.addMovie(movie);
        actor2.addMovie(movie2);

    }

    @Test
    public void testGetActor_met_correcte_naam_voornaam() {
       assertEquals(actor2.getId(), facade.getActor(actor2.getNaam(), actor2.getVoornaam()).getId());

    }

    @Test
    public void testRemoveActor_met_correcte_voornaam_naam_id_leeftijd_verwijdert_actor() {
        assertEquals(2, facade.getAantalActors());
        facade.deleteMoviesWithSpeceficActor(actor);
        facade.removeActor(actor);
        assertEquals(1, facade.getAantalActors());

    }

    @Test
    public void testUpdateActor_met_correcte_voornaam_naam_id_leeftijd_update_actor_en_update_film_met_hoofdrolspeler() {
        Movie movieNew= new Movie("Mobie",45,actor);
        facade.addMovie(movieNew);
        actor.addMovie(movieNew);
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
    public void testUpdateMovie_met_correcte_titel_duur_hoofdrolspeler_update_movie_en_update_bijhorende_film_van_bijhorende_actor() {
        assertEquals(movie2.getDuur(), facade.getMovie(movie2).getDuur());
        facade.updateMovie(new Movie("Into the wild", 110, actor2, movie2.getId()));
        assertEquals(110, facade.getMovie(movie2).getDuur());
       assertEquals(110, facade.getMoviesWithSpecificActor(actor2).get(0).getDuur());

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

    @Test(expected = DbException.class)
    public void testRemoveMovie_met_null_waarden_geeft_DbExcecption() {
        facade.removeMovie(null);
        assertEquals(1, facade.getAantalMovies());

    }

    @Test(expected = DbException.class)
    public void testRemoveActor_geeft_DbExcecption_wanneer_de_acteur_met_id_niet_bestaat() {
        facade.removeActor(new Actor("Johnny", "Depp", 55, 13));
        assertEquals(1, facade.getAantalMovies());

    }

    @Test(expected = DbException.class)
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
        actor2.addMovie(movie);
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
        actor.addMovie(movieThriller);
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
    
   
    

}
