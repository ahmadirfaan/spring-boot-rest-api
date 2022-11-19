package com.irfaan.danspro.service.impl;

import com.irfaan.danspro.entities.Users;
import com.irfaan.danspro.repositories.UserRepository;
import com.irfaan.danspro.service.UserService;
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
