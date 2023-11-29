package com.irfaan.restapi.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginRequest {

    @NotBlank(message = "username tidak boleh ada yang kosong")
    private String username;

    @NotBlank(message = "password tidak boleh ada yang kosong")
    private String password;
}
