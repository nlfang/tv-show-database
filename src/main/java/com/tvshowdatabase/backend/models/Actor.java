package com.tvshowdatabase.backend.models;

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
@Table(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int actorID;

    @Column (name = "actorName")
    private String name;

    @Column (name = "actorDOB")
    private String DateOfBirth;

    /* connect to Actor-TVShow relationship */
    @OneToMany(mappedBy="actor")
    private Set<ActsIn> actsIns;

    public int getActorID() {
        return this.actorID;
    }

    public String getName() {
        return this.name;
    }

    public String getDateOfBirth() {
        return this.DateOfBirth;
    }
}
