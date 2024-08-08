package com.tutorial.bike_service.controller;

import com.tutorial.bike_service.entity.Bike;
import com.tutorial.bike_service.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {
    @Autowired
    BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> bikes = bikeService.getAll();
        if (bikes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getByid(@PathVariable("id") int id) {
        Bike bike = bikeService.getById(id);
        if (bike == null) {
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bike);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId) {
        List<Bike> bikes = bikeService.getByUserId(userId);
        return ResponseEntity.ok(bikes);
    }

    @PostMapping
    public ResponseEntity<Bike> save(@RequestBody Bike bike) {
        Bike newBike = bikeService.save(bike);
        return ResponseEntity.ok(newBike);
    }
}
