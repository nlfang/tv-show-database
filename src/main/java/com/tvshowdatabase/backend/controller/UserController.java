package com.tvshowdatabase.backend.controller;

import java.util.List;

import com.tvshowdatabase.backend.models.TVShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
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

    /**
     * Justin Stewart
     * 
     * get user's email using their username
     * 
     * ISOLATION LEVEL EXPLANATION: READ COMMITTED because we want to make sure 
     * we are retrieving a committed email since that is a more important 
     * piece of information than favorite shows or something. There's no reason 
     * to move up to repeatable read because there's only one query within this 
     * transaction, so repeatable read wouldn't add anything helpful.
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @GetMapping("/emailByUser/{username}")
    public String getEmailByUsername(@PathVariable("username") String username) {
        return userRepository.getEmailByUsername(username); // returns null if username doesn't exist
    }

    /**
     * Justin Stewart
     * 
     * change user's username
     * 
     * ISOLATION LEVEL EXPLANATION: SERIALIZABLE because we need to lock the entire table 
     * to prevent someone else from adding this username at the same time (usernames should be unique).
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @PutMapping("/changeUsername/{old}/{new}")
    public String changeUsername(@PathVariable("old") String oldUsername, @PathVariable("new") String newUsername) {
        List<User> lu = userRepository.findByUsername(newUsername);
        if ( lu.isEmpty() ) {
            // update username
            User u = userRepository.findByUsername(oldUsername).get(0); // assume one user since usernames should be unique
            u.setUsername(newUsername);
            userRepository.save(u);
            return "success";
        }
        // already exists
        return null;
    }

    /**
     * Justin Stewart
     * 
     * change user's email
     * 
     * ISOLATION LEVEL EXPLANATION: SERIALIZABLE because we need to lock the entire table 
     * to prevent someone else from adding this email at the same time (emails should be unique).
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @PutMapping("/changeEmail/{username}/{new}")
    public String changeEmail(@PathVariable("username") String username, @PathVariable("new") String newEmail) {
        List<User> lu = userRepository.findByEmail(newEmail);
        if ( lu.isEmpty() ) {
            // update email
            User u = userRepository.findByUsername(username).get(0); // assume one user since usernames should be unique
            u.setEmail(newEmail);
            userRepository.save(u);
            return "success";
        }
        // already exists
        return null;
    }

}
