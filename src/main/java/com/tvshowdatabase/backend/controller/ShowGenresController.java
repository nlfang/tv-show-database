package com.tvshowdatabase.backend.controller;

import java.util.List;
import java.util.Map;

import com.tvshowdatabase.backend.models.Directs;
import com.tvshowdatabase.backend.models.show_genres;
import com.tvshowdatabase.backend.models.Genre;
import com.tvshowdatabase.backend.models.TVShow;
import com.tvshowdatabase.backend.repository.DirectorRepository;
import com.tvshowdatabase.backend.repository.GenreRepository;
import com.tvshowdatabase.backend.repository.ShowGenresRepository;
import com.tvshowdatabase.backend.repository.TVShowRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class ShowGenresController {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TVShowRepository tvShowRepository;

    @Autowired
    private ShowGenresRepository showGenresRepository;

    /**
     * Nicholas Fang
     *
     * Add a show genre mapping to the database
     *
     * ISOLATION LEVEL EXPLANATION: SERIALIZABLE ensures that when TV shows' genres are being added to the database,
     * other instances of this transaction aren't being allowed through. This helps to prevent the chance of
     * inserting two of the same TV shows' genre into the database, and also prevents phantom data from entering the
     * database.
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @PostMapping("/addshowgenre/{genreName}/{showName}")
    public ResponseEntity<show_genres> addShowGenres (@PathVariable("genreName") String genreName,
                                                    @PathVariable("showName") String showName) {
        int genreID = genreRepository.getGenreID(genreName);
        int showID = tvShowRepository.getTVShowIDByName(showName);

        show_genres showGenres = new show_genres(genreID, showID);
        return new ResponseEntity<show_genres>(showGenresRepository.save(showGenres), HttpStatus.OK);
    }
}
