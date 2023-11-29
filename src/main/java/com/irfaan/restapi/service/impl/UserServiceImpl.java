package com.irfaan.restapi.service.impl;

import com.irfaan.restapi.entities.Users;
import com.irfaan.restapi.models.UserRequestDTO;
import com.irfaan.restapi.repositories.UserRepository;
import com.irfaan.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    protected final UserRepository usersRepository;

    protected final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Users verifyUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public Users addUser(UserRequestDTO userRequestDTO) {
        Users users = new Users();
        users.setUsername(users.getUsername());
        users.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));
        return usersRepository.save(users);
    }

}
