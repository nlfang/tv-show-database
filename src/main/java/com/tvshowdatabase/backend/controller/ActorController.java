package com.tvshowdatabase.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addactor")
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor) {
        System.out.println("Made it to addactor");
        System.out.println(actor.getActorName());
        System.out.println(actor.getDateOfBirth());
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

}
