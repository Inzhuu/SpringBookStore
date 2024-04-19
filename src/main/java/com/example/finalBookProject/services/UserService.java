package com.example.finalBookProject.services;

import com.example.finalBookProject.entities.Users;
import org.apache.catalina.User;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Users getUserByEmail(String email);

    Users createUser(Users user);

    Users findByUsername(String s);


}
