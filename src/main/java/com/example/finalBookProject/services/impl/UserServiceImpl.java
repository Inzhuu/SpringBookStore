package com.example.finalBookProject.services.impl;

import com.example.finalBookProject.entities.Roles;
import com.example.finalBookProject.entities.Users;
import com.example.finalBookProject.repositories.RoleRepository;
import com.example.finalBookProject.repositories.UserRepository;
import com.example.finalBookProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users myUser = userRepository.findByEmail(s).orElse(null);
        if (myUser != null) {
            User secUser = new User(myUser.getEmail(), myUser.getPassword(), myUser.getRoles());
            return secUser;
        }
        throw new UsernameNotFoundException("User Not Found");
    }

    @Override
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Users createUser(Users user) {
        Users checkUser = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (checkUser == null) {
            Roles role = roleRepository.findByRole("ROLE_USER");
            if (role != null) {
                ArrayList<Roles> roles = new ArrayList<>();
                roles.add(role);
                user.setRoles(roles);
                return userRepository.save(user);
            }
        }
        return null;
    }

    public Users findByUsername(String s){
        Optional<Users> users = userRepository.findByEmail(s);

        return users.orElse(null);
    }

}
