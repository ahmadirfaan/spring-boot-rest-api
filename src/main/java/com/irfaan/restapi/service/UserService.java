package com.irfaan.restapi.service;

import com.irfaan.restapi.entities.Users;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: UserService.java, v 0.1 2021‐10‐20 08.09 Ahmad Irfaan Hibatullah Exp $$
 */
public interface UserService {

    Users verifyUsername(String username);

}