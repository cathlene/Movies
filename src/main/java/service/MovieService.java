/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.Movie.MovieRepository;
import domain.Actor;
import domain.Movie;
import db.Movie.MovieFactory;
import java.util.List;

/**
 *
 * @author cathlene
 */
public class MovieService {
     private MovieRepository movieRepository;

    public MovieService(String repository){
             movieRepository= new MovieFactory().createRepository(repository);

    }
     public List<Movie> getMoviesWithSpecificDuration(int duur){
        return movieRepository.getMoviesWithSpecificDuration(duur);
    }

    public List<Movie>getMovies(){
    return movieRepository.getAllMovies();
    }
    
     public List<Movie> getMoviesWithSpecificActor(Actor actor){
        return movieRepository.getMoviesWithSpecificActor(actor);
    }
      public Movie getMovie(Movie movie){
        return movieRepository.getMovie(movie);
    }
       public Movie getMovie(long id){
        return movieRepository.getMovie(id);
    }
       public void removeMovie(Movie movie){
        movieRepository.removeMovie(movie);
    }
    public MovieRepository getMovieRepository(){
        return this.movieRepository;
    }
     public void updateMovie(Movie movie){
        movieRepository.updateMovie(movie);
    }
      public void addMovie(Movie movie){
        movieRepository.addMovie(movie);
    }
       public int getAantalMovies(){
    return movieRepository.getAantalMovies();
    }
}
