package com.example.userservice.repository;

import com.example.userservice.model.User;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByRole(String role);
}
