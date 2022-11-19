package com.irfaan.danspro.service;

import com.irfaan.danspro.models.UserLoginRequest;

import java.util.Map;

public interface LoginService {

    Map<String,String> userLogin(UserLoginRequest request);
}
