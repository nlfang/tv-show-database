package com.tvshowdatabase.backend.models;

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
    private int showID;

    @Column (name = "name")
    private String name;

    //@Column (name = "genreID")
    //private int genreID;

    @Column (name = "length")
    private int length;

    @Column (name = "year_of_release")
    private int yearOfRelease;

    @Column (name = "rating")
    private int rating;

    @Column (name = "director")
    private String director;

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
        return this.yearOfRelease;
    }

    public int getRating() {
        return this.rating;
    }

    public String getDirector() {
        return this.director;
    }
}
