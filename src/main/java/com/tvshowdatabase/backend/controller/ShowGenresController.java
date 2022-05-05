package com.tvshowdatabase.backend.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.datasource.url}")
    private String springDatasourceUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String springDatasourceDriverClassName;

    @Value("${spring.datasource.username}")
    private String springDatasourceUsername;

    @Value("${spring.datasource.password}")
    private String springDatasourcePassword;

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
    public ResponseEntity<String> addShowGenres (@PathVariable("genreName") String genreName,
                                                    @PathVariable("showName") String showName) {
        int genreID = genreRepository.getGenreID(genreName);
        int showID = tvShowRepository.getTVShowIDByName(showName);

        String addString = "INSERT INTO show_genres (genreID, showID) VALUES (?, ?)";
        try ( Connection conn = DriverManager.getConnection(
                springDatasourceUrl, springDatasourceUsername, springDatasourcePassword);
              PreparedStatement preparedStatement = conn.prepareStatement(addString)) {

            preparedStatement.setInt(1, genreID);
            preparedStatement.setInt(2, showID);
            boolean result = preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Exception occurred, failed to add TV Show", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
}
