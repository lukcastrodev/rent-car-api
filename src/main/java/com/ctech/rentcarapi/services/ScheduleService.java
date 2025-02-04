package com.ctech.rentcarapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ctech.rentcarapi.dtos.ScheduleRequestDTO;
import com.ctech.rentcarapi.dtos.ScheduleResponseDTO;
import com.ctech.rentcarapi.dtos.mappers.CarMapper;
import com.ctech.rentcarapi.dtos.mappers.ScheduleMapper;
import com.ctech.rentcarapi.dtos.mappers.UserMapper;
import com.ctech.rentcarapi.exceptions.RecordNotFoundException;
import com.ctech.rentcarapi.models.Car;
import com.ctech.rentcarapi.models.Schedule;
import com.ctech.rentcarapi.models.User;
import com.ctech.rentcarapi.repositories.CarRepository;
import com.ctech.rentcarapi.repositories.ScheduleRepository;
import com.ctech.rentcarapi.repositories.UserRepository;

import io.micrometer.common.lang.NonNull;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;

@Service
public class ScheduleService {

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserMapper userMapper;
    private final CarMapper carMapper;
    private final ScheduleMapper scheduleMapper;

    public ScheduleService(UserRepository userRepository,
            CarRepository carRepository,
            ScheduleRepository scheduleRepository,
            UserMapper userMapper,
            CarMapper carMapper,
            ScheduleMapper scheduleMapper
    ){
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.scheduleRepository = scheduleRepository;
        this.userMapper = userMapper;
        this.carMapper = carMapper;
        this.scheduleMapper = scheduleMapper;
    }

    public List<ScheduleResponseDTO> list(){
        return this.scheduleRepository.findAll()
                .stream()
                .map(scheduleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void create(ScheduleRequestDTO scheduleDto){
        Schedule schedule = new Schedule();
        
        User user = this.userRepository.findById(scheduleDto.userId())
                .orElseThrow(() -> new RecordNotFoundException(scheduleDto.userId()));
        
        Car car = this.carRepository.findById(scheduleDto.carId())
                .orElseThrow(() -> new RecordNotFoundException(scheduleDto.carId()));
        
        car.setRented(true);

        schedule.setUser(user);
        schedule.setCar(car);
        schedule.setRentInitialDate(scheduleDto.rentInitialDate());
        schedule.setRentExpirationDate(scheduleDto.rentExpirationDate());

        this.carRepository.save(car);
        this.scheduleRepository.save(schedule);
    }

    public void delete(Long scheduleId) {
        this.scheduleRepository.delete(this.scheduleRepository.findById(scheduleId)
                .map(recordFound -> {
                    Car car = recordFound.getCar();
                    car.setRented(false);
                    this.carRepository.save(car);
                    return recordFound;
                })
                .orElseThrow(() -> new RecordNotFoundException(scheduleId)));
    }
}
