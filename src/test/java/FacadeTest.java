import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cathlene on 14/02/2016.
 */
public class FacadeTest {

    private  Actor actor;
    private Movie movie;
    private Facade facade;
    @org.junit.Before
    public void setUp() {

        actor= new Actor("Johnny", "Depp", 55,"John");
        movie= new Movie("PublicEnemy", 118,actor);
       facade= new Facade();

    }

    @Test
    public void testGetter_actor_met_geldige_waarden(){
    assertEquals(actor.getId(), facade.getActor("John").getId());

    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetter_actor_met_fouten_waarden(){
        assertEquals(actor.getId(), facade.getActor("Joh").getId());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetter_movie_met_fouten_waarden(){
        assertEquals(actor.getId(), facade.getMovie("p").getTitle());

    }

    @Test
    public void testGetter_movie_met_geldige_waarden(){
        assertEquals(movie.getTitle(), facade.getMovie("PublicEnemy").getTitle());

    }
    @Test
    public void testAdd_movie_met_juiste_parameters_voegt_movie_toe(){

        MovieRepository movieRepository = new MovieRepositoryStub();
        movieRepository.addMovie(movie);
        assertEquals(1,movieRepository.getAantalMovies());
    }

    @Test
    public void testAdd_actor_met_juiste_parameters_voegt_actor_toe(){

        ActorRepository actorRepository = new ActorRepositoryStub();
        actorRepository.addActor(actor);
        assertEquals(1,actorRepository.getAantalActors());
    }

    @Test
    public void testGetMoviesWithSPecificActor_geeft_lijst_van_films_terug_met_de_specifieke_geldige_acteur(){
       List<Movie> movies=facade.getMoviesWithSpecificActor(actor);
        assertEquals("John",movies.get(0).getHoofdrolSpeler().getId());
    }

    @Test
    public void testGetMoviesWithSPecificActor_geeft__lege_lijst_van_films_terug_met_de_specifieke_ongeldige_acteur(){
        List<Movie> movies=facade.getMoviesWithSpecificActor(new Actor("Jeniffer", "Andi",53,"Jen"));
        assertTrue(movies.isEmpty());
    }

    @Test
    public void testGetMoviesWithSPecificDuration_geeft_lijst_van_films_terug_met_de_specifieke_juiste_duur_limiet(){
        List<Movie> movies=facade.getMoviesWithSpecificDuration(120);
        assertEquals("PublicEnemy", movies.get(0).getTitle());
    }
    @Test
    public void testGetMoviesWithSPecificDuration_geeft_lege_lijst_van_films_terug_met_de_specifieke_foute_duur_limiet_geeft_niks_terug(){
        List<Movie> movies=facade.getMoviesWithSpecificDuration(100);
        assertTrue(movies.isEmpty());
    }
    @org.junit.After
    public void tearDown()  {

    }

}