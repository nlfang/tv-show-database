package com.tvshowdatabase.backend.repository;

import com.tvshowdatabase.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByUsername(String username);

    List<User> findByEmail(String email);

    @Query(value = "SELECT email FROM users WHERE username = ?1", nativeQuery = true)
    String getEmailByUsername(String username);

}
