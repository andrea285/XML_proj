package com.example.userservice.repository;

import com.example.userservice.model.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FollowRepository extends MongoRepository<Follow, String> {
    List<Follow> findByFollowerId(String followerId);
    List<Follow> findByFolloweeId(String followeeId);
    void deleteByFollowerIdAndFolloweeId(String followerId, String followeeId);
}
