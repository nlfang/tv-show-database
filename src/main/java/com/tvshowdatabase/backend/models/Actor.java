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
    @Column(name = "actorid")
    private int actorID;

    @Column (name = "actorName")
    private String actorName;

    @Column (name = "actorDOB")
    private String actorDOB;

    /* connect to Actor-TVShow relationship */
    @OneToMany(mappedBy="actor")
    private Set<ActsIn> actsIns;

    public Actor(String roleActorName, String s) {
        this.actorName = roleActorName;
        this.actorDOB = s;
    }

    public int getActorID() {
        return this.actorID;
    }

    public String getActorName() {
        return this.actorName;
    }

    public String getDateOfBirth() {
        return this.actorDOB;
    }
}
