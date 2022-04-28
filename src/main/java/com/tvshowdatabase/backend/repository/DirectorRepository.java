package com.tvshowdatabase.backend.repository;

import java.util.List;
import java.util.Map;

import com.tvshowdatabase.backend.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface DirectorRepository extends JpaRepository<Director, Integer> {

    /* Gets 3 most frequent directors for the user */
    @Query(value = "SELECT d.director_name, COUNT(t.showid) AS count FROM directors d " +
                    "INNER JOIN shows_directors sd ON d.directorid = sd.directorid " + 
                    "INNER JOIN tv_shows t ON t.showid = sd.showid " + 
                    "INNER JOIN users_tvshows ut ON t.showid = ut.showid " + 
                    "INNER JOIN users u ON ut.userid = u.userid " +
                    "WHERE u.username = ?1 " + 
                    "GROUP BY d.director_name " + 
                    "ORDER BY count DESC " + 
                    "LIMIT 3", nativeQuery = true)
    List<Map<String, Integer>> getTopDirectors(String username);

}
