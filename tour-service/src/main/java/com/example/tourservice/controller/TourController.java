package com.example.tourservice.controller;

import com.example.tourservice.model.Tour;
import com.example.tourservice.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private TourRepository tourRepository;

    @GetMapping
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    @PostMapping
    public Tour createTour(@RequestBody Tour tour) {
        return tourRepository.save(tour);
    }
}
