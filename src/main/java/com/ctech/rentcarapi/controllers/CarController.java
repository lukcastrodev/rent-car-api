package com.ctech.rentcarapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctech.rentcarapi.dtos.CarDTO;
import com.ctech.rentcarapi.services.CarService;

import jakarta.validation.constraints.NotNull;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/car")
public class CarController {
    
    private final CarService carService;

    public CarController(CarService carService){
        this.carService = carService;
    }

    @GetMapping
    public List<CarDTO> list() {
        return this.carService.list();
    }
    
    @GetMapping("/{id}")
    public CarDTO listById(@PathVariable @NotNull Long id) {
        return this.carService.listById(id);
    }

    @PostMapping
    public CarDTO create(@RequestBody @NotNull CarDTO car) {
        return this.carService.create(car);
    }

    @PutMapping("/{id}")
    public CarDTO update(@PathVariable Long id, @RequestBody CarDTO car) {
        return this.carService.update(id, car);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull Long id){
        this.carService.delete(id);
    }
}
