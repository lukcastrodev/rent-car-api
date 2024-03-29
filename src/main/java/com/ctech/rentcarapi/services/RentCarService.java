package com.ctech.rentcarapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ctech.rentcarapi.dtos.CarDTO;
import com.ctech.rentcarapi.dtos.RentCarDTO;
import com.ctech.rentcarapi.dtos.UserDTO;
import com.ctech.rentcarapi.dtos.mappers.CarMapper;
import com.ctech.rentcarapi.dtos.mappers.UserMapper;
import com.ctech.rentcarapi.exceptions.RecordNotFoundException;
import com.ctech.rentcarapi.models.Car;
import com.ctech.rentcarapi.models.User;
import com.ctech.rentcarapi.repositories.CarRepository;
import com.ctech.rentcarapi.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class RentCarService {
    
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final UserMapper userMapper;
    private final CarMapper carMapper;

    public RentCarService(UserRepository userRepository,
            CarRepository carRepository,
            UserMapper userMapper,
            CarMapper carMapper
    ){
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.userMapper = userMapper;
        this.carMapper = carMapper;
    }

    @Transactional
    public void rentCar(RentCarDTO rent){
        
        User user = this.userRepository.findById(rent.userId())
                .orElseThrow(() -> new RecordNotFoundException(rent.userId()));
        
        Car car = this.carRepository.findById(rent.carId())
                .orElseThrow(() -> new RecordNotFoundException(rent.carId()));
        
        user.getCars().add(car);
        car.getUsers().add(user);
 
        car.setRentDate(rent.rentDate());
        car.setExpirationDate(rent.expirationDate());
        car.setRented(true);
        
        this.userRepository.save(user);
        this.carRepository.save(car);
    }

    public List<UserDTO> findUsersByCarId(Long id) {
        Car car = this.carRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));

        return this.userRepository.findByCars(car)
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CarDTO> findCarsByUserId(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));

        return this.carRepository.findByUsers(user)
                .stream()
                .map(carMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void cancelRent(Long userId, Long carId){
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException(userId));

        Car car = this.carRepository.findById(carId)
                .orElseThrow(() -> new RecordNotFoundException(carId));

        user.getCars().remove(car);
        car.getUsers().remove(user);
        car.setRentDate(null);
        car.setExpirationDate(null);
        car.setRented(false);

        this.userRepository.save(user);
        this.carRepository.save(car);
    }
}
