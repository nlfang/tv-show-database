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
    @Query(value = "SELECT g.genre_name, COUNT(t.showid) AS count FROM genres g " +
                    "INNER JOIN shows_genres sg ON g.genreid = sg.genreid " + 
                    "INNER JOIN tv_shows t ON t.showid = sg.showid " + 
                    "INNER JOIN users_tvshows ut ON t.showid = ut.showid " + 
                    "INNER JOIN users u ON ut.userid = u.userid " +
                    "WHERE u.username = ?1 " + 
                    "GROUP BY g.genre_name " + 
                    "ORDER BY count DESC " + 
                    "LIMIT 3", nativeQuery = true)
    List<Map<String, Object>> getTopGenres(String username);

}
