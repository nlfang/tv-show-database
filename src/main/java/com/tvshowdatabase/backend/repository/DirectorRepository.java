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
    @Query(value = "SELECT d.directorName, COUNT(t.showID) AS count FROM directors d " +
                    "INNER JOIN directs sd ON d.directorID = sd.directorID " + 
                    "INNER JOIN tv_shows t ON t.showID = sd.showID " + 
                    "INNER JOIN favorites fav ON t.showID = fav.showID " + 
                    "INNER JOIN users u ON fav.userID = u.userid " +
                    "WHERE u.username = ?1 " + 
                    "GROUP BY d.directorName " + 
                    "ORDER BY count DESC " + 
                    "LIMIT 3", nativeQuery = true)
    List<Map<String, Integer>> getTopDirectors(String username);

    @Query(value = "SELECT d.directorID FROM directors d WHERE d.director_name = ?1", nativeQuery = true)
    int getDirectorID(String directorName);
}
