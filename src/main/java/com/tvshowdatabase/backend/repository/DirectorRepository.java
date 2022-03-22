package com.tvshowdatabase.backend.repository;

import com.tvshowdatabase.backend.models.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DirectorRepository extends JpaRepository<TVShow, Integer> {
}
