
package domain;
import db.Actor.ActorRepository;
import db.Actor.ActorRepositoryStub;
import db.DbException;
import domain.*;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cathlene on 14/02/2016.
 */
public class FacadeTest {

    private Actor actor;
    private Movie movie;
    private Facade facade;
    
    @org.junit.Before
    public void setUp() {

        actor= new Actor("Johnny", "Depp", 55,"John");
        movie= new Movie("PublicEnemies", 118,actor);
       facade= new Facade("stub");

    }

    @Test
    public void testGetter_actor_met_correcte_naam(){
    assertEquals(actor.getId(), facade.getActor("John").getId());

    }
    @Test
    public void testRemoveActor_met_correcte_voornaam_naam_id_leeftijd_verwijdert_actor(){
        facade.addActor(actor);
        assertEquals(1,facade.getActorRepository().getAantalActors());
        facade.removeActor(actor);
        assertEquals(0,facade.getActorRepository().getAantalActors());

    }

    @Test
    public void testUpdateActor_met_correcte_voornaam_naam_id_leeftijd_update_actor(){
        assertEquals(55, facade.getActorRepository().getActor("John").getLeeftijd());
      facade.getActorRepository().updateActor(new Actor("Johnny", "Depp", 56,"John"));
        assertEquals(56, facade.getActorRepository().getActor("John").getLeeftijd());
    }

    @Test
    public void testUpdateMovie_met_correcte_titel_duur_hoofdrolspeler_update_movie(){
        assertEquals(118, facade.getMovieRepository().getMovie("PublicEnemies").getDuur());
        facade.getMovieRepository().updateMovie(new Movie("PublicEnemies", 110,actor));
        assertEquals(110,facade.getMovieRepository().getMovie("PublicEnemies").getDuur());
    }

    @Test
    public void testRemoveMovie_met_correcte_titel_duur_hoofdrolspeler_verwijdert_movie(){
        facade.addMovie(movie);
        assertEquals(2,facade.getMovieRepository().getAantalMovies());
        facade.removeMovie(movie);
        assertEquals(1,facade.getMovieRepository().getAantalMovies());

    }

    @Test(expected=DbException.class)
    public void testRemoveMovie_geeft_DbExcecption_wanneer_movie_met_bepaalde_titel_niet_bestaat(){
        facade.removeMovie(new Movie("Ranbo", 96, new Actor("Johnny", "Depp",55,"John")));
        assertEquals(1,facade.getMovieRepository().getAantalMovies());

    }
    @Test(expected=DbException.class)
    public void testRemoveMovie_met_null_waarden_geeft_DbExcecption(){
        facade.removeMovie(null);
        assertEquals(1,facade.getMovieRepository().getAantalMovies());

    }
    @Test(expected=DbException.class)
    public void testRemoveActor_geeft_DbExcecption_wanneer_de_acteur_met_id_niet_bestaat(){
        facade.removeActor( new Actor("Johnny", "Depp", 55,"Joh"));
        assertEquals(1,facade.getMovieRepository().getAantalMovies());

    }
    @Test(expected=DbException.class)
    public void testRemoveActor_met_null_waarden_geeft_DbExcecption(){
        facade.removeActor(null);
        assertEquals(1,facade.getMovieRepository().getAantalMovies());

    }

    @Test(expected = DbException.class)
    public void testGetActor_met_niet_geldige_id_gooit_DbException(){
        assertEquals(actor.getId(), facade.getActor("Joh").getId());

    }

    @Test(expected = DbException.class)
    public void testGetMovie_met_ongeldige_title_gooit_DbException(){
        assertEquals(movie.getTitle(), facade.getMovie("p").getTitle());

    }

    @Test
    public void testGetMovie_met_correcte_title_duur_hoofdrolSpeler(){
        assertEquals(movie.getTitle(), facade.getMovie("PublicEnemies").getTitle());

    }
    @Test
    public void testAdd_movie_met_correcte_voornaam_naam_id_leeftijd_voegt_movie_toe(){

        facade.getMovieRepository().addMovie(movie);
        assertEquals(2,facade.getMovieRepository().getAantalMovies());
    }

    @Test
    public void testAdd_actor_met_correcte_voornaam_naam_id_leeftijd_voegt_actor_toe(){

        ActorRepository actorRepository = new ActorRepositoryStub();
        actorRepository.addActor(actor);
        assertEquals(1,actorRepository.getAantalActors());
    }

    @Test
    public void testGetMoviesWithSPecificActor_geeft_lijst_van_films_terug_met_de_specifieke_geldige_acteur(){
       List<Movie> movies=facade.getMoviesWithSpecificActor(actor);
        assertEquals("John",movies.get(0).getHoofdrolSpeler().getId());
        assertEquals("John", movies.get(1).getHoofdrolSpeler().getId());
    }

    @Test
    public void testGetMoviesWithSPecificActor_geeft__lege_lijst_van_films_terug_met_de_specifieke_ongeldige_acteur(){
        List<Movie> movies=facade.getMoviesWithSpecificActor(new Actor("Jeniffer", "Andi",53,"Jen"));
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
    @org.junit.After
    public void tearDown()  {

    }

}