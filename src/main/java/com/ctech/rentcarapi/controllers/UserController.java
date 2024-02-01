package com.ctech.rentcarapi.controllers;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.*;

import com.ctech.rentcarapi.dtos.UserDTO;
import com.ctech.rentcarapi.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
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
    
    @PatchMapping("/{id}")
    public UserDTO update(@PathVariable @NotNull Long id, @RequestBody @Valid @NotNull UserDTO user) {
        return this.userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull Long id) {
        this.userService.delete(id);
    }
}
