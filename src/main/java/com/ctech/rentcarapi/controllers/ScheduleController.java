package com.ctech.rentcarapi.controllers;

import com.ctech.rentcarapi.dtos.ScheduleRequestDTO;
import com.ctech.rentcarapi.dtos.ScheduleResponseDTO;
import com.ctech.rentcarapi.services.ScheduleService;

import jakarta.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    
    @PostMapping
    public void create(@RequestBody ScheduleRequestDTO scheduleDto) {
        this.scheduleService.create(scheduleDto);
    }

    @GetMapping
    public List<ScheduleResponseDTO> list() {
        return this.scheduleService.list();
    }
    
    // @GetMapping("/users/{id}")
    // public List<UserDTO> listUsersByCarId(@PathVariable @NotNull Long id) {
    //     return this.rentCarService.findUsersByCarId(id);
    // }

    @DeleteMapping("cancel/{scheduleId}")
    public void delete(@PathVariable @NotNull Long scheduleId){
        this.scheduleService.delete(scheduleId);
    }
}
