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
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int genreID;

    @Column (name = "genre_name")
    private String name;

    /* many to many with TVShow */
    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<TVShow> tvshows = new HashSet<>();

    public int getGenreID() {
        return this.genreID;
    }

    public String getName() {
        return this.name;
    }
}
