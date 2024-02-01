package com.ctech.rentcarapi.controllers;

import com.ctech.rentcarapi.dtos.CarDTO;
import com.ctech.rentcarapi.dtos.RentCarDTO;
import com.ctech.rentcarapi.dtos.UserDTO;
import com.ctech.rentcarapi.services.RentCarService;

import jakarta.validation.constraints.NotNull;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/rent")
public class RentCarController {

    private final RentCarService rentCarService;
    
    public RentCarController(RentCarService rentCarService) {
        this.rentCarService = rentCarService;
    }
    
    @PostMapping
    public void rentCar(@RequestBody RentCarDTO rent) {
        this.rentCarService.rentCar(rent);
    }

    @GetMapping("/cars/{id}")
    public List<CarDTO> listCarsByUserId(@PathVariable @NotNull Long id) {
        return this.rentCarService.findCarsByUserId(id);
    }
    
    @GetMapping("/users/{id}")
    public List<UserDTO> listUsersByCarId(@PathVariable @NotNull Long id) {
        return this.rentCarService.findUsersByCarId(id);
    }

    @DeleteMapping("cancel/{userId}/{carId}")
    public void cancelRent(@PathVariable Long userId, @PathVariable Long carId){
        this.rentCarService.cancelRent(userId, carId);
    }
}
