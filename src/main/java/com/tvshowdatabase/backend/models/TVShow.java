package com.tvshowdatabase.backend.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tv_shows")
public class TVShow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "showID")
    private int showID;

    @Column (name = "name")
    private String name;

    @Column (name = "length")
    private int length;

    @Column (name = "year_of_release")
    private int year_of_release;

    @Column (name = "rating")
    private int rating;

    /* many to many with User */
    @ManyToMany(mappedBy = "tvshows", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    /* many to many with Genre */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "show_genres",
            joinColumns = {
                    @JoinColumn(name = "showID", referencedColumnName = "showID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "genreID", referencedColumnName = "genreID",
                            nullable = false, updatable = false)})
    private Set<Genre> genres = new HashSet<>();

    /* many to many with Director */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "directs",
            joinColumns = {
                    @JoinColumn(name = "showID", referencedColumnName = "showID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "directorID", referencedColumnName = "directorID",
                            nullable = false, updatable = false)})
    private Set<Director> directors = new HashSet<>();

    /* connect to Actor-TVShow relationship */
    @OneToMany(mappedBy="tvshow")
    private Set<ActsIn> actsIns;

    public TVShow(int showID, String showName, int length, int year_of_release, int rating) {
        this.showID = showID;
        this.name = showName;
        this.length = length;
        this.year_of_release = year_of_release;
        this.rating = rating;
    }

    public int getShowID() {
        return this.showID;
    }

    public String getName() {
        return this.name;
    }

    public int getLength() {
        return this.length;
    }

    public int getYearOfRelease() {
        return this.year_of_release;
    }

    public int getRating() {
        return this.rating;
    }
}
