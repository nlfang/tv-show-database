package com.tvshowdatabase.backend.controller;

import java.util.List;
import java.util.Map;

import com.tvshowdatabase.backend.models.Genre;
import com.tvshowdatabase.backend.repository.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class GenreController {
    
    @Autowired
    private GenreRepository genreRepository;

    @PostMapping ("/addgenre/{genre_name}")
    public ResponseEntity<Genre> addGenre(@PathVariable("genre_name") String genreName) {
        System.out.println("Made it to addGenre");
        Genre genre = new Genre(genreName);
        return new ResponseEntity<Genre>(genreRepository.save(genre), HttpStatus.OK);
    }
    /**
     * Justin Stewart
     * 
     * get top 3 most frequent genres within a user's favorite shows
     * 
     * ISOLATION LEVEL EXPLANATION: READ UNCOMMITTED because there's no reason to bother with locks.
     * This is just an interesting, unimportant mini-list for users to see, so it's not  
     * necessary to sacrifice speed in order to guarantee consistency/accuracy. Also,
     * only one user (the owner of this list) can alter it (by favoriting/unfavoriting shows),
     * meaning it won't happen often where someone is looking at another user's page at the same 
     * time that they're altering their favorite-show list.
     */
    @GetMapping("/topGenres/{username}")
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<Map<String, Integer>> getTopGenresByUsername(@PathVariable("username") String username) {
        return genreRepository.getTopGenres(username);
    }

}
