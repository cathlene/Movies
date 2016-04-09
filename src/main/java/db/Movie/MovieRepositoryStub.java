package db.Movie;

import db.DbException;
import domain.Actor;
import domain.Movie;

import java.util.*;

/**
 * Created by cathlene on 8/02/2016.
 */
public class MovieRepositoryStub implements MovieRepository{
    
        private static int counter = 0;

    private static Map<Long, Movie> movies;
    public MovieRepositoryStub(){

        movies= new TreeMap<Long, Movie>(); // films worden gesorteerd volgens titel bijgehouden

        this.addMovie( new Movie("PublicEnemies", 118,new Actor("Johnny", "Depp", 55)));
        this.addMovie(new Movie("Rango", 96, new Actor("Johnny", "Depp",55)));
    }
     public static int getID(){
        counter++;
        return counter;
    }
    public void addMovie(Movie movie) {
        if (movie==null){
            throw new DbException("Geen geldige movie");
        }
        movie.setId(MovieRepositoryStub.getID());
        movies.put(movie.getId(),movie);

    }

    public void removeMovie(Movie movie) {
        if (movie==null || !movies.containsKey(movie.getId())){
            throw new DbException("Geen geldige movie");
        }
        movies.remove(movie.getId());
    }

    public void updateMovie(Movie movie) {
        if (movie==null || !movies.containsKey(movie.getId())){
            throw new DbException("Geen geldige movie");
        }
        movies.put(movie.getId(),movie);

    }
    public Movie getMovie(Movie movie) {
        if (movie==null || !movies.containsKey(movie.getId())){
            throw new DbException("Geen geldige film");
        }
        return movies.get(movie.getId());
    }
     public Movie getMovie(long id) {
        if (!movies.containsKey(id)){
            throw new DbException("Geen geldige Id");
        }
        return movies.get(id);
    }
    public List<Movie> getMoviesWithSpecificDuration(int duur){
        List<Movie> moviesWithDuration =getAllMovies();
        ListIterator<Movie> itr=moviesWithDuration.listIterator();
        while(itr.hasNext()){
            if(itr.next().getDuur()> duur){
                itr.remove();
            }
        }
        return moviesWithDuration;
    }
    public List<Movie> getMoviesWithSpecificActor(Actor actor){
                
        List<Movie> moviesWithActor =getAllMovies();
        ListIterator<Movie> itr=moviesWithActor.listIterator();
        while(itr.hasNext()){
            Actor actor2=itr.next().getHoofdrolSpeler();
        
        if(!actor.equals(actor2)){
            itr.remove();
            }
        }
        return moviesWithActor;

    }
    public List<Movie> getAllMovies(){
        return new ArrayList<Movie>(movies.values());
    }
    public int getAantalMovies(){
        return movies.size();
    }

    public void clearData() {
     movies=  movies= new TreeMap<Long, Movie>();

    }

    public void deleteMoviesWithSpecificActor(Actor actor) {
    List<Movie> moviesWithActor =getAllMovies();
        ListIterator<Movie> itr=moviesWithActor.listIterator();
        while(itr.hasNext()){
            Movie movie=itr.next();
            Actor actor2=movie.getHoofdrolSpeler();
        
        if(actor.equals(actor2)){
            itr.remove();
            actor.deleteMovie(movie);
            }
        }
    }
}
