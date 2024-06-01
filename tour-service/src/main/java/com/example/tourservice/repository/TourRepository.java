package com.example.tourservice.repository;

import com.example.tourservice.model.Tour;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends MongoRepository<Tour, String> {
}
