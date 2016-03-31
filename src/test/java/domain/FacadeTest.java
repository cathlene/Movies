
package domain;
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
public class FacadeTest {

    private Actor actor,actor2,actor3;
    private Movie movie,movie2,movie3;
    private Facade facade;
    
    @org.junit.Before
    public void setUp() {

        actor= new Actor("Johnny", "Depp", 55);
        actor2= new Actor("Willie","Bend",43);
        actor3= new Actor("Gertje","Verhul",20);
        
        movie= new Movie("PublicEnemies", 118,actor);
        movie2= new Movie("Into the wild",120,actor2);
        movie3= new Movie("Smurfen",96,actor3);
        
       facade= new Facade("stub");
       facade.addActor(actor);

    }
 
    @Test
    public void testGetActor_met_correcte_naam_voornaam(){
     facade.addActor(actor2);
    assertEquals(actor2.getId(), facade.getActor(actor2.getNaam(),actor2.getVoornaam()).getId());
    
    }
    @Test
    public void testRemoveActor_met_correcte_voornaam_naam_id_leeftijd_verwijdert_actor(){
        facade.addActor(actor);
        assertEquals(3,facade.getActorRepository().getAantalActors());
        facade.removeActor(actor);
        assertEquals(2,facade.getActorRepository().getAantalActors());

    }

    @Test
    public void testUpdateActor_met_correcte_voornaam_naam_id_leeftijd_update_actor(){
        assertEquals(55, facade.getActorRepository().getActor("Depp","Johnny").getLeeftijd());
      facade.getActorRepository().updateActor(new Actor("Johnny", "Depp", 56));
        assertEquals(56, facade.getActorRepository().getActor("Depp","Johnny").getLeeftijd());
    }

    @Test
    public void testUpdateMovie_met_correcte_titel_duur_hoofdrolspeler_update_movie(){
        facade.addMovie(movie2);
        assertEquals(movie2.getDuur(), facade.getMovieRepository().getMovie(movie2).getDuur());
        facade.getMovieRepository().updateMovie(new Movie("Into the wild", 110,actor2,movie2.getId()));
        assertEquals(110,facade.getMovieRepository().getMovie(movie2).getDuur());
    }

    @Test
    public void testRemoveMovie_met_correcte_titel_duur_hoofdrolspeler_verwijdert_movie(){
        facade.addMovie(movie);
        assertEquals(3,facade.getMovieRepository().getAantalMovies());
        facade.removeMovie(movie);
        assertEquals(2,facade.getMovieRepository().getAantalMovies());

    }

    @Test(expected=DbException.class)
    public void testRemoveMovie_geeft_DbExcecption_wanneer_movie_met_bepaalde_titel_niet_bestaat(){
        facade.removeMovie(new Movie("Ranbo", 96, new Actor("Johnny", "Depp",55,20)));
        assertEquals(1,facade.getMovieRepository().getAantalMovies());

    }
    @Test(expected=DbException.class)
    public void testRemoveMovie_met_null_waarden_geeft_DbExcecption(){
        facade.removeMovie(null);
        assertEquals(1,facade.getMovieRepository().getAantalMovies());

    }
    @Test(expected=DbException.class)
    public void testRemoveActor_geeft_DbExcecption_wanneer_de_acteur_met_id_niet_bestaat(){
        facade.removeActor( new Actor("Johnny", "Depp", 55,13));
        assertEquals(1,facade.getMovieRepository().getAantalMovies());

    }
    @Test(expected=DbException.class)
    public void testRemoveActor_met_null_waarden_geeft_DbExcecption(){
        facade.removeActor(null);
        assertEquals(1,facade.getMovieRepository().getAantalMovies());

    }



    @Test
    public void testGetMovie_met_correcte_title_duur_hoofdrolSpeler(){
        facade.addMovie(movie);
    assertEquals(movie, facade.getMovie(movie));

    }
    @Test
    public void testAdd_movie_met_correcte_voornaam_naam_id_leeftijd_voegt_movie_toe(){

        facade.getMovieRepository().addMovie(movie);
        assertEquals(3,facade.getMovieRepository().getAantalMovies());
    }

    @Test
    public void testAddActor_met_correcte_voornaam_naam_id_leeftijd_voegt_actor_toe(){

        ActorRepository actorRepository = new ActorRepositoryStub();
        actorRepository.addActor(actor);
        assertEquals(2,actorRepository.getAantalActors());
    }

    @Test
    public void testGetMoviesWithSPecificActor_geeft_lijst_van_films_terug_met_de_specifieke_geldige_acteur(){
       List<Movie> movies=facade.getMoviesWithSpecificActor(actor);
        assertEquals("PublicEnemies",movies.get(0).getTitle());
        assertEquals("Rango", movies.get(1).getTitle());
    }

    @Test
    public void testGetMoviesWithSPecificActor_geeft__lege_lijst_van_films_terug_met_de_specifieke_ongeldige_acteur(){
        List<Movie> movies=facade.getMoviesWithSpecificActor(new Actor("Jeniffer", "Andi",53));
        assertTrue(movies.isEmpty());
    }

    @Test
    public void testGetMoviesWithSPecificDuration_geeft_lijst_van_films_terug_met_de_specifieke_juiste_duur_limiet(){
        List<Movie> movies=facade.getMoviesWithSpecificDuration(120);
        assertEquals("PublicEnemies", movies.get(0).getTitle());
        assertEquals("Rango", movies.get(1).getTitle());
    }
    @Test
    public void testGetMoviesWithSPecificDuration_geeft_lege_lijst_van_films_terug_met_de_specifieke_foute_duur_limiet(){
        List<Movie> movies=facade.getMoviesWithSpecificDuration(80);
        assertTrue(movies.isEmpty());
    }

    @Test
    public void testGetMoviesWithSPecificDuration_geeft__lijst_van_films_terug_met_de_specifieke__duur_limiet(){
        List<Movie> movies=facade.getMoviesWithSpecificDuration(100);
        assertEquals("Rango",movies.get(0).getTitle());
    }
    @Test
    public void testGetMovies_returns_list_with_all_Movies(){
    
        List<Movie> movies= facade.getMovies();
        assertEquals(2, movies.size());
    }
    

}