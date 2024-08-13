package com.tutorial.user_service.feignclients;

import com.tutorial.user_service.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service")
public interface CarFeignClient {
    @PostMapping("/car")
    Car save(@RequestBody Car car);

    @GetMapping("/car/byuser/{userId}")
    List<Car> getCars(@PathVariable("userId") int userId);
}
