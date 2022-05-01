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
@Table(name = "directs")
public class Directs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "directsID")
    private int directsID;

    @Column (name = "directorID")
    private int directorID;

    @Column (name = "showID")
    private int showID;


    public Directs(int directorID, int showID) {
        this.directorID = directorID;
        this.showID = showID;
    }
}
