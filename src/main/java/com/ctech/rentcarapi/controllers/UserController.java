package com.ctech.rentcarapi.controllers;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.*;

import com.ctech.rentcarapi.dtos.CarDTO;
import com.ctech.rentcarapi.dtos.UserDTO;
import com.ctech.rentcarapi.services.RentCarService;
import com.ctech.rentcarapi.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final RentCarService rentCarService;

    public UserController(
        UserService userService,
        RentCarService rentCarService){
        this.userService = userService;
        this.rentCarService = rentCarService;
    }
    
    @GetMapping
    public List<UserDTO> list() {
        return this.userService.list();
    }

    @GetMapping("/{id}")
    public UserDTO listById(@PathVariable @NotNull Long id) {
        return this.userService.listById(id);
    }

    @PostMapping
    public UserDTO create(@RequestBody @NotNull UserDTO user) {
        return this.userService.create(user);
    }
    
    @GetMapping("/rented-car/{id}")
    public List<CarDTO> listCarsByUserId(@PathVariable @NotNull Long id) {
        return this.rentCarService.findCarsByUserId(id);
    }

    @PostMapping("rent-car/{userId}/{carId}")
    public void rentCar(
            @PathVariable @NotNull Long userId,
            @PathVariable @NotNull Long carId
    ) {
        this.rentCarService.rentCar(userId, carId);
    }
    
    @PatchMapping("/{id}")
    public UserDTO update(@PathVariable @NotNull Long id, @RequestBody @Valid @NotNull UserDTO user) {
        return this.userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull Long id) {
        this.userService.delete(id);
    }
}
