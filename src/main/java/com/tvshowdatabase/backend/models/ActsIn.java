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
@Table(name = "acted")
public class ActsIn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int actsInID;

    @Column(name = "character_name")
    private String characterName;

    /* connects to Actor */
    @ManyToOne
    @JoinColumn(name="actorID", nullable=false)
    private Actor actor;

    /* connects to TVShow */
    @ManyToOne
    @JoinColumn(name="showID", nullable=false)
    private TVShow tvshow;

    public ActsIn(String charName, Actor actor, TVShow tvshow) {
        this.characterName = charName;
        this.actor = actor;
        this.tvshow = tvshow;
    }
}
