package com.tvshowdatabase.backend.repository;

import java.util.List;

import com.tvshowdatabase.backend.models.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface TVShowRepository extends JpaRepository<TVShow, Integer> {

    /* Queries to get a user's favorite shows sorted on an attribute */

    @Query("SELECT DISTINCT t FROM TVShow t JOIN FETCH t.users JOIN t.users us " + 
            "WHERE us.username = ?1 ORDER BY t.name ASC")
    List<Object> getFavoriteShowsName(String username);

    @Query("SELECT DISTINCT t FROM TVShow t JOIN FETCH t.users JOIN t.users us " + 
            "WHERE us.username = ?1 ORDER BY t.name DESC")
    List<Object> getFavoriteShowsNameDesc(String username);

    @Query("SELECT DISTINCT t FROM TVShow t JOIN FETCH t.users JOIN t.users us " + 
            "WHERE us.username = ?1 ORDER BY t.rating ASC")
    List<Object> getFavoriteShowsRating(String username);

    @Query("SELECT DISTINCT t FROM TVShow t JOIN FETCH t.users JOIN t.users us " + 
            "WHERE us.username = ?1 ORDER BY t.rating DESC")
    List<Object> getFavoriteShowsRatingDesc(String username);

    @Query("SELECT DISTINCT t FROM TVShow t JOIN FETCH t.users JOIN t.users us " + 
            "WHERE us.username = ?1 ORDER BY t.length ASC")
    List<Object> getFavoriteShowsLength(String username);

    @Query("SELECT DISTINCT t FROM TVShow t JOIN FETCH t.users JOIN t.users us " + 
            "WHERE us.username = ?1 ORDER BY t.length DESC")
    List<Object> getFavoriteShowsLengthDesc(String username);

    @Query("SELECT DISTINCT t FROM TVShow t JOIN FETCH t.users JOIN t.users us " + 
            "WHERE us.username = ?1 ORDER BY t.year_of_release ASC")
    List<Object> getFavoriteShowsYear(String username);

    @Query("SELECT DISTINCT t FROM TVShow t JOIN FETCH t.users JOIN t.users us " + 
            "WHERE us.username = ?1 ORDER BY t.year_of_release DESC")
    List<Object> getFavoriteShowsYearDesc(String username);

}
