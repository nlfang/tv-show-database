package com.tvshowdatabase.backend.repository;

import com.tvshowdatabase.backend.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DirectorRepository extends JpaRepository<Director, Integer> {
}
