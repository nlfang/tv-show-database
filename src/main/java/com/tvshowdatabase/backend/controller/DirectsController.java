package com.tvshowdatabase.backend.controller;

import java.util.List;
import java.util.Map;

import com.tvshowdatabase.backend.models.ActsIn;
import com.tvshowdatabase.backend.models.Director;
import com.tvshowdatabase.backend.models.Directs;
import com.tvshowdatabase.backend.models.TVShow;
import com.tvshowdatabase.backend.repository.DirectorRepository;
import com.tvshowdatabase.backend.repository.DirectsRepository;
import com.tvshowdatabase.backend.repository.TVShowRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.tvshowdatabase.backend.repository.ActsInRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class DirectsController {
    @Autowired
    private DirectsRepository directsRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private TVShowRepository tvShowRepository;

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
//    @Transactional(isolation = Isolation.SERIALIZABLE)
//    @PostMapping("/adddirectorrole/{roleDirectorName}/{directsShowName}")
//    public ResponseEntity<Directs> addDirectorRole (@PathVariable("roleDirectorName") String dirName,
//                                                    @PathVariable("directsShowName") String showName) {
//        int directorID = directorRepository.getDirectorID(dirName);
//        int showID = tvShowRepository.getTVShowIDByName(showName);
//
//        Directs directs = new Directs(directorID, showID);
//        return new ResponseEntity<Directs>(directsRepository.save(directs), HttpStatus.OK);
//    }
}
