package com.irfaan.restapi.service.impl;

import com.irfaan.restapi.entities.Users;
import com.irfaan.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAuthenticationServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserAuthenticationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userService.verifyUsername(username);
        if (users != null && users.getUsername().equals(username)) {
            return new User(users.getUsername(), users.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
