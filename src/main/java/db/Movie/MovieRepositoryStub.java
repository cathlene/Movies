package db.Movie;

import db.DbException;
import domain.Actor;
import domain.Movie;

import java.util.*;

/**
 * Created by cathlene on 8/02/2016.
 */
public class MovieRepositoryStub implements MovieRepository{
    private Map<String, Movie> movies;
    public MovieRepositoryStub(){

        movies= new TreeMap<String, Movie>(); // films worden gesorteerd volgens titel bijgehouden

        this.addMovie( new Movie("PublicEnemies", 118,new Actor("Johnny", "Depp", 55,"John")));
        this.addMovie(new Movie("Rango", 96, new Actor("Johnny", "Depp",55,"John")));

    }
    public void addMovie(Movie movie) {
        if (movie==null){
            throw new DbException("Geen geldige movie");
        }
        movies.put(movie.getTitle(),movie);

    }

    public void removeMovie(Movie movie) {
        if (!movies.containsKey(movie.getTitle())){
            throw new DbException("Geen geldige movie");
        }
        movies.remove(movie.getTitle());
    }

    public void updateMovie(Movie movie) {
        if (!movies.containsKey(movie.getTitle())){
            throw new DbException("Geen geldige movie");
        }
        movies.put(movie.getTitle(),movie);

    }


    public Movie getMovie(String title) {
        if (title==null || title.isEmpty()|| !movies.containsKey(title)){
            throw new DbException("Geen geldige title");
        }
        return movies.get(title);
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
            if(!actor.getId().equals(itr.next().getHoofdrolSpeler().getId())){
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
}
