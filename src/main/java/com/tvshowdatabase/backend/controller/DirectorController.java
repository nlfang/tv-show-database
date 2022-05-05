package com.tvshowdatabase.backend.controller;

import java.util.List;
import java.util.Map;

import com.tvshowdatabase.backend.models.Actor;
import com.tvshowdatabase.backend.models.Director;
import com.tvshowdatabase.backend.models.Directs;
import com.tvshowdatabase.backend.repository.DirectorRepository;

import com.tvshowdatabase.backend.repository.TVShowRepository;
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

    @Autowired
    private TVShowRepository tvShowRepository;

    @GetMapping("/directors")
    public List<Director> getAllDirectors() {
        System.out.println("Reached get all directors");
        return directorRepository.findAll();
    }

    /**
     * Rithwik Palivela
     *
     * Get a director's shows by their id
     *
     * ISOLATION LEVEL EXPLANATION: READ_UNCOMMITTED because there's no reason to bother with locks.
     * We are only reading results here instead of performing any modifications. It is not at all
     * necessary in this case to sacrifice speed to guarantee consistency and accuracy.
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @GetMapping("directors/{directorID}")
    public List<Director> getDirectorByID(@PathVariable("directorID") int directorID) {
        System.out.println("Reached get director by id");
        return directorRepository.getDirectorByID(directorID);
    }

    /**
     * Rithwik Palivela
     *
     * Get a director's shows by their id
     *
     * ISOLATION LEVEL EXPLANATION: READ_UNCOMMITTED because there's no reason to bother with locks.
     * We are only reading results here instead of performing any modifications. It is not at all
     * necessary in this case to sacrifice speed to guarantee consistency and accuracy.
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @GetMapping("directors/{directorID}/{directorIDAgain}")
    public List<String> getShowsByDirectorID(@PathVariable("directorID") int directorID, @PathVariable("directorIDAgain") int directorIDAgain) {
        System.out.println("Reached get director's shows by id");
        return directorRepository.getShowsByDirectorID(directorID);
    }

    /**
     * Nicholas Fang
     *
     * Add a director to the database
     *
     * ISOLATION LEVEL EXPLANATION: SERIALIZABLE ensures that when directors are being added to the database,
     * other instances of this transaction aren't being allowed through. This helps to prevent the chance of
     * inserting two of the same director into the database, and also prevents phantom data from entering the
     * database.
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
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

    /**
     * Stanley Wang
     *
     * Get Directors using a search query
     *
     * ISOLATION LEVEL EXPLANATION: READ UNCOMMITTED since we want to prioritize speed over consistency here. We're
     * only reading and selecting data from the database, so there won't be any conflicts anyways if the database is
     * modified or updated. There's no need for locks to maintain accuracy.
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @GetMapping("directors/search/{searchQuery}")
    public List<Map<Director, String>> getActorSearch(@PathVariable("searchQuery") String searchQuery) {
        System.out.println("Reached searching Directors");
        System.out.println(directorRepository.getDirectorSearch(searchQuery));
        return directorRepository.getDirectorSearch(searchQuery);
    }

    /**
     * Nicholas Fang
     *
     * Add a director role to the database
     *
     * ISOLATION LEVEL EXPLANATION: SERIALIZABLE ensures that when director roles are being added to the database,
     * other instances of this transaction aren't being allowed through. This helps to prevent the chance of
     * inserting two of the same director role into the database, and also prevents phantom data from entering the
     * database.
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @PostMapping("/adddirectorrole/{roleDirectorName}/{directsShowName}")
    public ResponseEntity<String> addDirectorRole (@PathVariable("roleDirectorName") String dirName,
                                                    @PathVariable("directsShowName") String showName) {
        int directorID = directorRepository.getDirectorID(dirName);
        int showID = tvShowRepository.getTVShowIDByName(showName);

        String dirDOB = directorRepository.getDirectorDOB(directorID);

        Director director = new Director(directorID, dirName, dirDOB, showID, showName);
        directorRepository.save(director);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

}
