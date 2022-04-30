package com.tvshowdatabase.backend.repository;

import java.util.List;
import java.util.Map;

import com.tvshowdatabase.backend.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    /* Gets 3 most frequent genres for the user */
    @Query(value = "SELECT g.genre_name, COUNT(t.showID) AS count FROM genres g " +
                    "INNER JOIN show_genres sg ON g.genreID = sg.genreID " + 
                    "INNER JOIN tv_shows t ON t.showID = sg.showID " + 
                    "INNER JOIN favorites fav ON t.showID = fav.showID " + 
                    "INNER JOIN users u ON fav.userID = u.userid " +
                    "WHERE u.username = ?1 " + 
                    "GROUP BY g.genre_name " + 
                    "ORDER BY count DESC " + 
                    "LIMIT 3", nativeQuery = true)
    List<Map<String, Integer>> getTopGenres(String username);

}
