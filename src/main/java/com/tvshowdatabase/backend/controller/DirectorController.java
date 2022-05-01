package com.tvshowdatabase.backend.controller;

import java.util.List;
import java.util.Map;

import com.tvshowdatabase.backend.models.Actor;
import com.tvshowdatabase.backend.models.Director;
import com.tvshowdatabase.backend.repository.DirectorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class DirectorController {
    
    @Autowired
    private DirectorRepository directorRepository;

    
    @PostMapping("/adddirector")
    public ResponseEntity<Director> addDirector(@RequestBody Director director) {
        System.out.println("Made it to addDirector");
        return new ResponseEntity<Director>(directorRepository.save(director), HttpStatus.OK);
    }

    /**
     * Justin Stewart
     * 
     * get top 3 most frequent directors within a user's favorite shows
     * 
     * ISOLATION LEVEL EXPLANATION: READ UNCOMMITTED because there's no reason to bother with locks.
     * This is just an interesting, unimportant mini-list for users to see, so it's not  
     * necessary to sacrifice speed in order to guarantee consistency/accuracy. Also,
     * only one user (the owner of this list) can alter it (by favoriting/unfavoriting shows),
     * meaning it won't happen often where someone is looking at another user's page at the same 
     * time that they're altering their favorite-show list.
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @GetMapping("/topDirectors/{username}")
    public List<Map<String, Integer>> getTopDirectorsByUsername(@PathVariable("username") String username) {
        return directorRepository.getTopDirectors(username);
    }

}
