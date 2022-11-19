package com.irfaan.danspro.service.impl;

import com.irfaan.danspro.models.UserLoginRequest;
import com.irfaan.danspro.service.JwtTokenUtil;
import com.irfaan.danspro.service.LoginService;
import com.irfaan.danspro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    protected final UserDetailsService usersDetailService;

    protected final JwtTokenUtil jwtTokenUtil;
    protected final UserService userService;
    protected final AuthenticationManager authenticationManager;

    @Autowired
    public LoginServiceImpl(UserDetailsService usersDetailService, JwtTokenUtil jwtTokenUtil, UserService userService, AuthenticationManager authenticationManager) {
        this.usersDetailService = usersDetailService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }




    @Override
    public Map<String, String> userLogin(UserLoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //generate user details
        UserDetails userDetails = usersDetailService.loadUserByUsername(request.getUsername());


        //generate access token and refresh token
        String accessToken = jwtTokenUtil.generateToken(userDetails);

        //response message data
        Map<String, String> data = new HashMap<>();
        data.put("token", accessToken);
        return data;
    }
}
