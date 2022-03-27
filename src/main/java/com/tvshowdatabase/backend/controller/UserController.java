package com.tvshowdatabase.backend.controller;

import java.util.List;

import com.tvshowdatabase.backend.models.TVShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tvshowdatabase.backend.models.User;
import com.tvshowdatabase.backend.repository.UserRepository;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        System.out.println("Reached get all users");
        for (User x: userRepository.findAll()) {
            System.out.println(x.getUsername());
        }
        return userRepository.findAll();
    }

    @PostMapping("/signup")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        System.out.println("reached signup");
        System.out.println(user.getUsername());
        return new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        User userquery = userRepository.findByUsername(user.getUsername()).get(0);
        System.out.println(userquery.getUsername());
        System.out.println(userquery.getPassword());
        if (user.getPassword().equals(userquery.getPassword())) {
            return new ResponseEntity<String>("Successfully Logged in", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Failed to sign in", HttpStatus.UNAUTHORIZED);
        }
    }
}
