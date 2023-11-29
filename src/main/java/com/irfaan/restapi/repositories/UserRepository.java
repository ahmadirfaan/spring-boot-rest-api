package com.irfaan.restapi.repositories;

import com.irfaan.restapi.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Users findByUsername(String username);
}
