package com.tvshowdatabase.backend.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "show_genres")
public class show_genres {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int show_genre_id;

    @Column(name = "genreID")
    private int genreID;

    @Column(name = "showID")
    private int showID;

    public show_genres(int genreID, int showID) {
        this.genreID = genreID;
        this.showID = showID;
    }
}
