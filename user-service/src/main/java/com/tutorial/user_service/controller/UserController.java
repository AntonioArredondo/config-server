package com.tutorial.user_service.controller;

import com.tutorial.user_service.entity.Userx;
import com.tutorial.user_service.models.Bike;
import com.tutorial.user_service.models.Car;
import com.tutorial.user_service.service.UserService;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<Userx>> getAll() {
        List<Userx> users = userService.getAll();
        if (users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Userx> getById(@PathVariable("id") int id) {
        Userx user = userService.getUserById(id);
        if (user == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Userx> save(@RequestBody Userx user) {
        Userx userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId) {
        Userx user = userService.getUserById(userId);
        if (user == null) {
            ResponseEntity.notFound().build();
        }
        List<Car> userCars = userService.getCars(userId);
        if (userCars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userCars);
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId) {
        Userx user = userService.getUserById(userId);
        if (user == null) {
            ResponseEntity.notFound().build();
        }
        List<Bike> userBikes = userService.getBikes(userId);
        if (userBikes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userBikes);
    }

    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car) {
        if (userService.getUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        Car newCar = userService.saveCar(userId, car);
        return ResponseEntity.ok(newCar);
    }

    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike) {
        if (userService.getUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        Bike newBike = userService.saveBike(userId, bike);
        return ResponseEntity.ok(bike);
    }

    @GetMapping("/getall/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId") int userId) {
        Map<String, Object> result = userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(result);
    }
}
