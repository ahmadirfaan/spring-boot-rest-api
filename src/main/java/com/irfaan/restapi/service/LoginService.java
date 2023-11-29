package com.irfaan.restapi.service;

import com.irfaan.restapi.models.UserLoginRequest;

import java.util.Map;

public interface LoginService {

    Map<String,String> userLogin(UserLoginRequest request);
}
