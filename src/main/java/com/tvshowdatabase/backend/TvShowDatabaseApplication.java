package com.tvshowdatabase.backend;

import com.tvshowdatabase.backend.repository.TVShowRepository;
import com.tvshowdatabase.backend.models.TVShow;
import com.tvshowdatabase.backend.repository.UserRepository;
import com.tvshowdatabase.backend.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TvShowDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TvShowDatabaseApplication.class, args);
	}

	@Autowired
	private TVShowRepository tvShowRepository;
//	@Override
//	public void run (String... args) throws Exception {
//		tvShowRepository.save(new TVShow(1, "Test show 1", 1, 123, 1990, 5, "Director 1"));
//		tvShowRepository.save(new TVShow(2, "Test show 2", 2, 456, 1991, 4, "Director 2"));
//		tvShowRepository.save(new TVShow(3, "Test show 3", 3, 789, 1992, 3, "Director 3"));
//	}
}
