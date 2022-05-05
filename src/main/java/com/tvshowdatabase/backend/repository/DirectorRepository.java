package com.tvshowdatabase.backend.repository;

import java.util.List;
import java.util.Map;

import com.tvshowdatabase.backend.models.Actor;
import com.tvshowdatabase.backend.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "SELECT d.* FROM directors d WHERE d.directorID = ?1", nativeQuery = true)
    Director getDirectorByID(@Param("directorID") int directorID);

    @Query(value = "SELECT t.name FROM directors d " +
                    "INNER JOIN directs sd ON d.directorID = sd.directorID " +
                    "INNER JOIN tv_shows t on t.showID = sd.showID " +
                    "WHERE d.directorID = ?1" +
                    "ORDER BY t.name", nativeQuery = true)
    List<String> getShowsByDirectorID(@Param("directorID") int directorID);

    @Query(value = "SELECT d.directorID FROM directors d WHERE d.director_name = ?1", nativeQuery = true)
    int getDirectorID(String directorName);

    @Query(value = "SELECT d.director_name FROM directors d WHERE d.directorID = ?1", nativeQuery = true)
    String getDirectorName(int directorID);

    @Query(value = "SELECT d.directorDOB FROM directors d WHERE d.directorID = ?1", nativeQuery = true)
    String getDirectorDOB(int directorID);

    @Query(value = "SELECT d.* FROM directors d WHERE d.director_name LIKE CONCAT('%', :director_name, '%')", nativeQuery = true)
    List<Map<Director, String>> getDirectorSearch(@Param("director_name") String searchQuery);
}
