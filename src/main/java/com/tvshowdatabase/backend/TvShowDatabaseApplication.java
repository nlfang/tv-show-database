package com.tvshowdatabase.backend;

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
	private UserRepository userRepository;
//	@Override
//	public void run (String... args) throws Exception {
//		userRepository.save(new User(1, "testUser", "testPassword", "testEmail@gmail.com"));
//		userRepository.save(new User(2, "testUser2", "testPassword2", "testEmail2@gmail.com"));
//		userRepository.save(new User(3, "testUser3", "testPassword3", "testEmail3@gmail.com"));
//	}
}
