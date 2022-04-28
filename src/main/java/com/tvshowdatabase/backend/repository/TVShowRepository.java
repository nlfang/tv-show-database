package com.tvshowdatabase.backend.repository;

import java.util.List;
import java.util.Map;

import com.tvshowdatabase.backend.models.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tvshowdatabase.backend.models.Genre;

@Repository

public interface TVShowRepository extends JpaRepository<TVShow, Integer> {

    /* Queries to get a user's favorite shows sorted on an attribute */

//     @Query("SELECT DISTINCT t FROM TVShow t JOIN FETCH t.users JOIN t.users us " + 
//             "WHERE us.username = ?1 ORDER BY t.name ASC")
//     List<Object> getFavoriteShowsName(String username);

    @Query(value = "SELECT t.*, GROUP_CONCAT(g.genre_name SEPARATOR ', ') AS genres FROM tv_shows t " +
                        "INNER JOIN users_tvshows ut ON t.showid = ut.showid " +
                        "INNER JOIN users u ON ut.userid = u.userid " +
                        "INNER JOIN shows_genres sg ON t.showid = sg.showid " +
                        "INNER JOIN genres g ON sg.genreid = g.genreid " +
                        "WHERE u.username = ?1 GROUP BY t.showid ORDER BY t.name ASC", nativeQuery = true)
    List<Map<TVShow, String>> getFavoriteShowsName(String username);

    @Query(value = "SELECT t.*, GROUP_CONCAT(g.genre_name SEPARATOR ', ') AS genres FROM tv_shows t " +
                        "INNER JOIN users_tvshows ut ON t.showid = ut.showid " +
                        "INNER JOIN users u ON ut.userid = u.userid " +
                        "INNER JOIN shows_genres sg ON t.showid = sg.showid " +
                        "INNER JOIN genres g ON sg.genreid = g.genreid " +
                        "WHERE u.username = ?1 GROUP BY t.showid ORDER BY t.name DESC", nativeQuery = true)
    List<Map<TVShow, String>> getFavoriteShowsNameDesc(String username);

    @Query(value = "SELECT t.*, GROUP_CONCAT(g.genre_name SEPARATOR ', ') AS genres FROM tv_shows t " +
                        "INNER JOIN users_tvshows ut ON t.showid = ut.showid " +
                        "INNER JOIN users u ON ut.userid = u.userid " +
                        "INNER JOIN shows_genres sg ON t.showid = sg.showid " +
                        "INNER JOIN genres g ON sg.genreid = g.genreid " +
                        "WHERE u.username = ?1 GROUP BY t.showid ORDER BY t.rating ASC", nativeQuery = true)
    List<Map<TVShow, String>> getFavoriteShowsRating(String username);

    @Query(value = "SELECT t.*, GROUP_CONCAT(g.genre_name SEPARATOR ', ') AS genres FROM tv_shows t " +
                        "INNER JOIN users_tvshows ut ON t.showid = ut.showid " +
                        "INNER JOIN users u ON ut.userid = u.userid " +
                        "INNER JOIN shows_genres sg ON t.showid = sg.showid " +
                        "INNER JOIN genres g ON sg.genreid = g.genreid " +
                        "WHERE u.username = ?1 GROUP BY t.showid ORDER BY t.rating DESC", nativeQuery = true)
    List<Map<TVShow, String>> getFavoriteShowsRatingDesc(String username);

    @Query(value = "SELECT t.*, GROUP_CONCAT(g.genre_name SEPARATOR ', ') AS genres FROM tv_shows t " +
                        "INNER JOIN users_tvshows ut ON t.showid = ut.showid " +
                        "INNER JOIN users u ON ut.userid = u.userid " +
                        "INNER JOIN shows_genres sg ON t.showid = sg.showid " +
                        "INNER JOIN genres g ON sg.genreid = g.genreid " +
                        "WHERE u.username = ?1 GROUP BY t.showid ORDER BY t.length ASC", nativeQuery = true)
    List<Map<TVShow, String>> getFavoriteShowsLength(String username);

    @Query(value = "SELECT t.*, GROUP_CONCAT(g.genre_name SEPARATOR ', ') AS genres FROM tv_shows t " +
                        "INNER JOIN users_tvshows ut ON t.showid = ut.showid " +
                        "INNER JOIN users u ON ut.userid = u.userid " +
                        "INNER JOIN shows_genres sg ON t.showid = sg.showid " +
                        "INNER JOIN genres g ON sg.genreid = g.genreid " +
                        "WHERE u.username = ?1 GROUP BY t.showid ORDER BY t.length DESC", nativeQuery = true)
    List<Map<TVShow, String>> getFavoriteShowsLengthDesc(String username);

    @Query(value = "SELECT t.*, GROUP_CONCAT(g.genre_name SEPARATOR ', ') AS genres FROM tv_shows t " +
                        "INNER JOIN users_tvshows ut ON t.showid = ut.showid " +
                        "INNER JOIN users u ON ut.userid = u.userid " +
                        "INNER JOIN shows_genres sg ON t.showid = sg.showid " +
                        "INNER JOIN genres g ON sg.genreid = g.genreid " +
                        "WHERE u.username = ?1 GROUP BY t.showid ORDER BY t.year_of_release ASC", nativeQuery = true)
    List<Map<TVShow, String>> getFavoriteShowsYear(String username);

    @Query(value = "SELECT t.*, GROUP_CONCAT(g.genre_name SEPARATOR ', ') AS genres FROM tv_shows t " +
                        "INNER JOIN users_tvshows ut ON t.showid = ut.showid " +
                        "INNER JOIN users u ON ut.userid = u.userid " +
                        "INNER JOIN shows_genres sg ON t.showid = sg.showid " +
                        "INNER JOIN genres g ON sg.genreid = g.genreid " +
                        "WHERE u.username = ?1 GROUP BY t.showid ORDER BY t.year_of_release DESC", nativeQuery = true)
    List<Map<TVShow, String>> getFavoriteShowsYearDesc(String username);

}
