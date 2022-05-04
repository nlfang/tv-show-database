package com.tvshowdatabase.backend.controller;

import java.util.List;
import java.util.Map;

import com.tvshowdatabase.backend.models.TVShow;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.tvshowdatabase.backend.models.ActsIn;
import com.tvshowdatabase.backend.repository.ActsInRepository;
import com.tvshowdatabase.backend.models.Actor;
import com.tvshowdatabase.backend.repository.ActorRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class ActorController {
    @Autowired
    private ActorRepository actorRepository;

    @GetMapping("/actors")
    public List<Actor> getAllActors() {
        System.out.println("Reached get all actors");
        return actorRepository.findAll();
    }

    /**
     * Rithwik Palivela
     *
     * Get an actor by their id
     *
     * ISOLATION LEVEL EXPLANATION: READ_UNCOMMITTED
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @GetMapping("/actors/{actorid}")
    public List<Map<Actor, Integer>> getActorByID(@PathVariable("actorid") int actorid) {
        return actorRepository.getActorByID(actorid);
    }

    /**
     * Nicholas Fang
     *
     * Add an actor to the database
     *
     * ISOLATION LEVEL EXPLANATION: SERIALIZABLE ensures that when actors are being added to the database,
     * other instances of this transaction aren't being allowed through. This helps to prevent the chance of
     * inserting two of the same actor into the database, and also prevents phantom data from entering the
     * database.
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @PostMapping("/addactor")
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor) {
        System.out.println("Made it to addactor");
        return new ResponseEntity<Actor>(actorRepository.save(actor), HttpStatus.OK);
    }
    /**
     * Justin Stewart
     * 
     * get top 3 most frequent actors within a user's favorite shows
     * 
     * ISOLATION LEVEL EXPLANATION: READ UNCOMMITTED because there's no reason to bother with locks.
     * This is just an interesting, unimportant mini-list for users to see, so it's not  
     * necessary to sacrifice speed in order to guarantee consistency/accuracy. Also,
     * only one user (the owner of this list) can alter it (by favoriting/unfavoriting shows),
     * meaning it won't happen often where someone is looking at another user's page at the same 
     * time that they're altering their favorite-show list.
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @GetMapping("/topActors/{username}")
    public List<Map<String, Integer>> getTopGenresByUsername(@PathVariable("username") String username) {
        return actorRepository.getTopActors(username);
    }

    /**
     * Stanley Wang
     *
     * Get Actors using a search query
     *
     * ISOLATION LEVEL EXPLANATION: READ UNCOMMITTED since we want to prioritize speed over consistency here. We're
     * only reading and selecting data from the database, so there won't be any conflicts anyways if the database is
     * modified or updated. There's no need for locks to maintain accuracy.
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @GetMapping("actors/search/{searchQuery}")
    public List<Map<Actor, String>> getActorSearch(@PathVariable("searchQuery") String searchQuery) {
        System.out.println("Reached searching Actors");
        System.out.println(actorRepository.getActorSearch(searchQuery));
        return actorRepository.getActorSearch(searchQuery);
    }

}
