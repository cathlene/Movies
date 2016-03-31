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
        Actor actor= new Actor("voro", "na", 45);
        facade.addActor(actor);

        Movie movie= new Movie("Spirit", 89, actor);

        movie.setGenre(Genre.Adventure);
                facade.addMovie(movie);

        actor.addMovie(movie);
               facade.updateActor(actor);

       facade.updateMovie(movie);
        System.out.println(facade.getActors());
        
    }
    
}
