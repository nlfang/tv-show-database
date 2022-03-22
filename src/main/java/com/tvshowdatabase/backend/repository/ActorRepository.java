package com.tvshowdatabase.backend.repository;

import com.tvshowdatabase.backend.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
