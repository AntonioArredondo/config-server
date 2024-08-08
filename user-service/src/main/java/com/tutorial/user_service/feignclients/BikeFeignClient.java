package com.tutorial.user_service.feignclients;

import com.tutorial.user_service.models.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "bike-service", url = "http://localhost:8003")
public interface BikeFeignClient {
    @PostMapping("/bike")
    Bike save(@RequestBody Bike bike);

    @GetMapping("/bike/byuser/{userId}")
    List<Bike> getBikes(@PathVariable("userId") int userId);
}