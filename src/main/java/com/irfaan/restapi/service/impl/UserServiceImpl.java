package com.irfaan.restapi.service.impl;

import com.irfaan.restapi.entities.Users;
import com.irfaan.restapi.repositories.UserRepository;
import com.irfaan.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    protected final UserRepository usersRepository;

    @Autowired
    public UserServiceImpl(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users verifyUsername(String username) {
        return usersRepository.findByUsername(username);
    }

}
