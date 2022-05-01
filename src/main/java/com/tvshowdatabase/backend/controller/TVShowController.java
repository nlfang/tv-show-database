package com.tvshowdatabase.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.tvshowdatabase.backend.models.Genre;
import com.tvshowdatabase.backend.models.TVShow;
import com.tvshowdatabase.backend.repository.TVShowRepository;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class TVShowController {
    @Autowired
    private TVShowRepository tvShowRepository;

    /**
     * Nicholas Fang
     *
     * Gets all TV shows from the database in no particular order
     *
     * ISOLATION LEVEL EXPLANATION: READ UNCOMMITTED. This transaction is only reading from the database, so it is
     * not necessary for it to have any locks. It is also better for this transaction to be faster rather than
     * consistent and accurate, so it is ok to read any uncommitted changes that may occur from the adding TV shows
     * method.
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @GetMapping("/tvshows")
    public List<TVShow> getAllTVShows() {
        System.out.println("Reached get all TV Shows");
        return tvShowRepository.findAll();
    }

    /**
     * Nicholas Fang
     *
     * Gets TV shows from the database sorted by their rating in descending order
     *
     * ISOLATION LEVEL EXPLANATION: READ UNCOMMITTED. Since this transaction is being displayed on the Home page,
     * speed should be prioritized over accuracy in order to serve the page quickly to the user. Also, since this
     * transaction only reads from the database, it is not necessary for it to have any locks.
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @GetMapping("/tvshowspopular")
    public List<TVShow> getPopularTVShows() {
        System.out.println("Reached home page TV Shows");
        return tvShowRepository.getShowsByRating();
    }

    /**
     * Nicholas Fang
     *
     * Add a TV Show to the database
     *
     * ISOLATION LEVEL EXPLANATION: SERIALIZABLE ensures that when TV shows are being added to the database,
     * other instances of this transaction aren't being allowed through. This helps to prevent the chance of
     * inserting two of the same TV show into the database, and also prevents phantom data from entering the
     * database.
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @PostMapping("/addtvshow")
    public ResponseEntity<TVShow> addShow(@RequestBody TVShow tvShow) {
        System.out.println("Reached add TV show");
        return new ResponseEntity<TVShow>(tvShowRepository.save(tvShow), HttpStatus.OK);
    }

    /**
     * Justin Stewart
     * 
     * get full list of user's favorite tv shows
     * 
     * ISOLATION LEVEL EXPLANATION: READ UNCOMMITTED because there's no reason to bother with locks.
     * This list isn't important enough to sacrifice speed in order to be completely consistent/accurate.
     * Especially because the only one who can alter the list is the owner of the list, meaning that 
     * user (the owner) should never see an inconsistent list since there list shouldn't be changing while 
     * they retrieve it (and the owner of the list is obviously the most important to support wrt thir list).
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @GetMapping("/favorites/{username}/{sort}/{asc}")
    public List<Map<TVShow, String>> getFavorites(@PathVariable("username") String username, 
                                        @PathVariable("sort") String sort,
                                        @PathVariable("asc") int asc) {
        if ( sort.equals("rating") ) {
            if ( asc > 0 ) {
                return tvShowRepository.getFavoriteShowsRating(username);
            }
            return tvShowRepository.getFavoriteShowsRatingDesc(username);
        }

        if ( sort.equals("length") ) {
            if ( asc > 0 ) {
                return tvShowRepository.getFavoriteShowsLength(username);
            }
            return tvShowRepository.getFavoriteShowsLengthDesc(username);
        }

        if ( sort.equals("year") ) {
            if ( asc > 0 ) {
                return tvShowRepository.getFavoriteShowsYear(username);
            }
            return tvShowRepository.getFavoriteShowsYearDesc(username);
        }

        if ( asc > 0 )
            return tvShowRepository.getFavoriteShowsName(username);
    
        return tvShowRepository.getFavoriteShowsNameDesc(username);
    }

    /**
     * Stanley Wang
     *
     * Get a specific TV Show using its ID
     */
    @GetMapping("/tvshows/{showID}")
    public TVShow getTVShow(@PathVariable("showID") int showID) {
        System.out.println("Reached get specific TV Show: " + showID);
        System.out.println(tvShowRepository.getTVShowByID(showID));
        return tvShowRepository.getTVShowByID(showID);
    }
}
