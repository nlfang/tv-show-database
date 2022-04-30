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
@Table(name = "directors")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "directorID")
    private int directorID;

    @Column (name = "directorName")
    private String name;

    // Might need to change data type for DATE
    @Column (name = "directorDOB")
    private String DateOfBirth;

    /* many to many with TVShow */
    @ManyToMany(mappedBy = "directors", fetch = FetchType.LAZY)
    private Set<TVShow> tvshows = new HashSet<>();

    public int getDirectorID() {
        return this.directorID;
    }

    public String getName() {
        return this.name;
    }

    public String getDateOfBirth() {
        return this.DateOfBirth;
    }
}
