package com.tvshowdatabase.backend.repository;

import java.util.List;
import java.util.Map;

import com.tvshowdatabase.backend.models.Actor;
import com.tvshowdatabase.backend.models.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface ActorRepository extends JpaRepository<Actor, Integer> {

    /* Gets 3 most frequent actors for the user */
    @Query(value = "SELECT a.actorName, COUNT(t.showID) AS count FROM actors a " +
                    "INNER JOIN acted ai ON a.actorid = ai.actorID " + 
                    "INNER JOIN tv_shows t ON t.showID = ai.showID " + 
                    "INNER JOIN favorites fav ON t.showID = fav.showID " + 
                    "INNER JOIN users u ON fav.userID = u.userid " +
                    "WHERE u.username = ?1 " + 
                    "GROUP BY a.actorName " + 
                    "ORDER BY count DESC " + 
                    "LIMIT 3", nativeQuery = true)
    List<Map<String, Integer>> getTopActors(String username);

    @Query(value = "SELECT a.* FROM actors a WHERE a.actorid = ?1", nativeQuery = true)
    Actor getActorByID(@Param("actorID") int actorID);

    @Query(value = "SELECT a.actorid FROM actors a WHERE a.actor_name = ?1", nativeQuery = true)
    int getActorIDByName(String actorName);

    @Query(value = "SELECT a.actorDOB FROM actors a WHERE a.actor_name = ?1", nativeQuery = true)
    String getActorDOB(String actorName);

    @Query(value = "SELECT a.* FROM actors a WHERE a.actor_name LIKE CONCAT('%', :actor_name, '%')", nativeQuery = true)
    List<Map<Actor, String>> getActorSearch(@Param("actor_name") String searchQuery);
}
