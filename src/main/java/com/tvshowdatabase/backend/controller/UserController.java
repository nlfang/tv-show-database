package com.tvshowdatabase.backend.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.tvshowdatabase.backend.models.TVShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.datasource.url}")
    private String springDatasourceUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String springDatasourceDriverClassName;

    @Value("${spring.datasource.username}")
    private String springDatasourceUsername;

    @Value("${spring.datasource.password}")
    private String springDatasourcePassword;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        System.out.println("Reached get all users");
        for (User x: userRepository.findAll()) {
            System.out.println(x.getUsername());
        }
        return userRepository.findAll();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @PostMapping("/signup")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        System.out.println("reached signup");
        List<User> usernameCheck = userRepository.findByUsername(user.getUsername());
        if (!usernameCheck.isEmpty()) {
            System.out.println("Duplicate Username");
            return new ResponseEntity<String>("Username already in use, failed to sign up", HttpStatus.CONFLICT);
        }
        List<User> emailCheck = userRepository.findByEmail(user.getEmail());
        if (!emailCheck.isEmpty()) {
            System.out.println("Duplicate Email");
            return new ResponseEntity<String>("Email already in use, failed to sign up", HttpStatus.CONFLICT);
        }

        String addString = "INSERT INTO users (email, password, username) VALUES (?, ?, ?)";
        try ( Connection conn = DriverManager.getConnection(
                springDatasourceUrl, springDatasourceUsername, springDatasourcePassword);
              PreparedStatement preparedStatement = conn.prepareStatement(addString)) {

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUsername());
            boolean result = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Exception occurred, failed to sign up", HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Exception occurred, failed to sign up", HttpStatus.UNAUTHORIZED);

        }

        //System.out.println(user.getUsername());
        //return new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK);
        return new ResponseEntity<String>("Succesfully signed up", HttpStatus.OK);
    }


    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        List<User> userqueryList = userRepository.findByUsername(user.getUsername());
        // to prevent server crashing from index-out-of-bounds if username isn't in database
        if (userqueryList.isEmpty()) {
            return new ResponseEntity<String>("Failed to sign in", HttpStatus.UNAUTHORIZED);
        }
        User userquery = userqueryList.get(0);
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

    /**
     * Justin Stewart
     * 
     * change user's password
     * 
     * ISOLATION LEVEL EXPLANATION: REPEATABLE READ because, under read committed, their account info 
     * could be altered (e.g, sharing account with someone else) in between the read and the write, and 
     * this would cause the newly changed information to be reverted to how it was before (this is very 
     * unlikely to happen, it's just to be sure). Also, there's no need to lock the entire table with 
     * serializable since the transaction is always focusing on a single row, and we don't need to ensure
     * passwords to be unique (accounts can have same password).
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @PutMapping("/changePassword/{username}/{new}")
    public String changePassword(@PathVariable("username") String username, @PathVariable("new") String newPassword) {
        User u = userRepository.findByUsername(username).get(0);
        u.setPassword(newPassword);
        userRepository.save(u);
        return "success";
    }

}
