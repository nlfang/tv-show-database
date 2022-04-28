package com.tvshowdatabase.backend.repository;

import java.util.List;
import java.util.Map;

import com.tvshowdatabase.backend.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface ActorRepository extends JpaRepository<Actor, Integer> {

    /* Gets 3 most frequent actors for the user */
    @Query(value = "SELECT a.actor_name, COUNT(t.showid) AS count FROM actors a " +
                    "INNER JOIN acts_in ai ON a.actorid = ai.actorid " + 
                    "INNER JOIN tv_shows t ON t.showid = ai.showid " + 
                    "INNER JOIN users_tvshows ut ON t.showid = ut.showid " + 
                    "INNER JOIN users u ON ut.userid = u.userid " +
                    "WHERE u.username = ?1 " + 
                    "GROUP BY a.actor_name " + 
                    "ORDER BY count DESC " + 
                    "LIMIT 3", nativeQuery = true)
    List<Map<String, Integer>> getTopActors(String username);

}
