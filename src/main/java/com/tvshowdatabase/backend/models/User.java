package com.tvshowdatabase.backend.models;

import javax.persistence.*;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;

    @Column (name = "username")
    private String username;

    @Column (name = "password")
    private String password;

    @Column (name = "email")
    private String email;

    public User() {

    }
}
