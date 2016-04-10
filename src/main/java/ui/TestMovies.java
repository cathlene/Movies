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
        //facade.clearMovieData();
       // facade.clearActorData();
        //Actor actor= new Actor("voor", "john", 45);
        //Actor  actor2= new Actor("jef","de",21);
        //Movie  movie= new Movie("title",120,actor);
        //Movie  movie2= new Movie("The",45,actor2);
        //facade.addActor(actor);
        //facade.addActor(actor2);
        //facade.addMovie(movie);
        //facade.addMovie(movie2);
        //actor.addMovie(movie);
        //actor2.addMovie(movie2);
        
        //facade.deleteMoviesWithSpeceficActor(actor);
       

      // Actor actor= new Actor("voor", "john", 45);
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
      // Test getmovieswithduration System.out.println(facade.getMoviesWithSpecificDuration(100).size());
      // TEST  System.out.print(facade.getMoviesWithSpecificActor(actor).size());
     // Actor actor= new  Actor("tes", "tels", 10);
     // Movie movie=new Movie("lam", 112, actor);
     // facade.addActor(actor);
      //facade.addMovie(movie);
      //actor.addMovie(movie);
      // zeer belangrijk eerst movies verwijderen voor je de acteur verwijderd die in die film meespeelt ->  deleteMoviesWIthActor
     
        System.out.println(facade.getActors());
        
    }
    
}
