package com.tvshowdatabase.backend.repository;

import java.util.List;
import java.util.Map;

import com.tvshowdatabase.backend.models.Directs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
public interface DirectsRepository extends JpaRepository<Directs, Integer> {
}
