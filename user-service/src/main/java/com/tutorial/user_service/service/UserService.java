package com.tutorial.user_service.service;

import com.tutorial.user_service.entity.Userx;
import com.tutorial.user_service.feignclients.BikeFeignClient;
import com.tutorial.user_service.feignclients.CarFeignClient;
import com.tutorial.user_service.models.Bike;
import com.tutorial.user_service.models.Car;
import com.tutorial.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CarFeignClient carFeignClient;
    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<Userx> getAll() {
        return userRepository.findAll();
    }

    public Userx getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public Userx save(Userx user) {
        Userx userNew = userRepository.save(user);
        return userNew;
    }

    public List<Car> getCars(int userId) {
        List<Car> cars = restTemplate.getForObject("http://car-service/car/byuser/" + userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(int userId) {
        List<Bike> bikes = restTemplate.getForObject("http://bike-service/bike/byuser/" + userId, List.class);
        return bikes;
    }

    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }

    public Bike saveBike(int userId, Bike bike) {
        bike.setUserId(userId);
        Bike bikeNew = bikeFeignClient.save(bike);
        return bikeNew;
    }

    public Map<String, Object> getUserAndVehicles(int userId) {
        Map<String, Object> result = new HashMap<>();
        Userx user = userRepository.findById(userId).orElse(null);

        if (user == null){
            result.put("Mensaje", "No existe el usuario");
            return result;
        }
        result.put("User", user);

        List<Car> cars = carFeignClient.getCars(userId);
        if (cars.isEmpty()){
            result.put("Cars", "ese user no tiene coches");
        }
        else {
            result.put("Cars", cars);
        }

        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if (bikes.isEmpty()){
            result.put("Bikes", "Ese user no tiene motos");
        }
        else {
            result.put("Bikes", bikes);
        }

        return result;
    }
}
