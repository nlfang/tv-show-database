package com.tvshowdatabase.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tvshowdatabase.backend.models.TVShow;
import com.tvshowdatabase.backend.repository.TVShowRepository;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class TVShowController {
    @Autowired
    private TVShowRepository tvShowRepository;
    @GetMapping("/tvshows")
    public List<TVShow> getAllTVShows() {
        System.out.println("Reached get all TV Shows");
        return tvShowRepository.findAll();
    }

    @PostMapping("/tvshows")
    public ResponseEntity<TVShow> addShow(@RequestBody TVShow tvShow) {
        System.out.println(tvShow.getShowID() + "Show ID");
        return new ResponseEntity<TVShow>(tvShowRepository.save(tvShow), HttpStatus.OK);
    }
}
