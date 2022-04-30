package com.tvshowdatabase.backend.controller;

import java.util.List;
import java.util.Map;

import com.tvshowdatabase.backend.models.Actor;
import com.tvshowdatabase.backend.models.TVShow;
import com.tvshowdatabase.backend.repository.ActorRepository;
import com.tvshowdatabase.backend.repository.TVShowRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.tvshowdatabase.backend.models.ActsIn;
import com.tvshowdatabase.backend.repository.ActsInRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class ActsInController {
    @Autowired
    private ActsInRepository actsInRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private TVShowRepository tvShowRepository;

    @PostMapping("/addactorrole/{characterName}/{roleActorName}/{showName}")
    public ResponseEntity<ActsIn> addActorRole(@PathVariable("characterName") String charName,
                                               @PathVariable("roleActorName") String roleActorName,
                                               @PathVariable("showName") String showName) {
        //This function doesn't work
        System.out.println(charName);
        System.out.println(roleActorName);
        System.out.println(showName);
        Actor actor = actorRepository.getActorByName(roleActorName);
        TVShow tvshow = tvShowRepository.getTVShowByName(showName);
        ActsIn actsIn = new ActsIn(charName, actor, tvshow);
        return new ResponseEntity<ActsIn>(actsInRepository.save(actsIn), HttpStatus.OK);
    }
}
