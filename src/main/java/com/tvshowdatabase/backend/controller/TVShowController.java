package com.tvshowdatabase.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tvshowdatabase.backend.models.TVShow;
import com.tvshowdatabase.backend.repository.TVShowRepository;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class TVShowController {
    @Autowired
    private TVShowRepository tvShowRepository;
    @GetMapping("/tvshows")
    public List<TVShow> getAllTVShows() {
        return tvShowRepository.findAll();
    }
}
