package com.tutorial.bike_service.service;

import com.tutorial.bike_service.entity.Bike;
import com.tutorial.bike_service.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {
    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    public Bike getById(int id) {
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike save(Bike bike) {
        Bike newBike = bikeRepository.save(bike);
        return newBike;
    }

    public List<Bike> getByUserId(int userId) {
        return bikeRepository.findByUserId(userId);
    }
}
