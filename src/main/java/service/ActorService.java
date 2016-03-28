/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.Actor.ActorRepository;
import db.Actor.DbActorType;
import domain.Actor;
import domain.ActorFactory;
import domain.Movie;
import java.util.List;

/**
 *
 * @author cathlene
 */
public class ActorService {
         private ActorRepository actorRepository;
         
         public ActorService(String repository){
         actorRepository= new ActorFactory().createRepository(repository);
         }

        public void addActor(Actor actor){
        actorRepository.addActor(actor);
    }
    public void removeActor(Actor actor){
        actorRepository.removeActor(actor);
    }
   
    public void updateActor(Actor actor){
        actorRepository.updateActor(actor);
    }
   
    public ActorRepository getActorRepository(){
        return this.actorRepository;
    }

   
    public Actor getActor(String id){
        return actorRepository.getActor(id);
    }

    public List<Actor> getActors() {
        return actorRepository.getAllActors();
    }

    public void saveOrUpdate(Actor actor) {
         actorRepository.saveOrUpdate(actor);
    }
    public int getAantalActors(){
    return actorRepository.getAantalActors();
    }
}
