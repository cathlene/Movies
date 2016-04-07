/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domain.Actor;
import domain.Facade;
import domain.Genre;
import domain.Movie;

/**
 *
 * @author cathlene
 */
public class TestMovies {
    public static void main(String[] args) {
        Facade facade= new Facade("sql");
      //  Actor actor= new Actor("voor", "john", 45);
       // facade.addActor(actor);
       
       // Test getActorByName           System.out.println(facade.getActor("john", "voor").toString());
       // Test GetActorById             System.out.println(facade.getActor(301).toString());
       // Test GetAantalActors          System.out.println(facade.getAantalActors());
       // Test removeActorById/Actor    facade.removeActor(301);
       // Test getMovieById             System.out.println(facade.getMovie(152));
       // Test getAantalMovies          System.out.println(facade.getAantalMovies());
       // Movie movie= new Movie("Spirit", 89, facade.getActor(101));

       
      //  movie.setGenre(Genre.Adventure);
       //         facade.addMovie(movie);

       // actor.addMovie(movie);
        //       facade.updateActor(actor);

       //facade.updateMovie(movie);
      //?????  System.out.println(facade.getMoviesWithSpecificDuration(100).size());
      // TEST  System.out.print(facade.getMoviesWithSpecificActor(actor).size());
     // Actor actor= new  Actor("tes", "tels", 10);
     // Movie movie=new Movie("lam", 112, actor);
     // facade.addActor(actor);
      //facade.addMovie(movie);
      //actor.addMovie(movie);
     
 
        System.out.println(facade.getActors());
        
    }
    
}
