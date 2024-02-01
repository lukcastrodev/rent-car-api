package com.ctech.rentcarapi.services;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ctech.rentcarapi.dtos.CarDTO;
import com.ctech.rentcarapi.dtos.mappers.CarMapper;
import com.ctech.rentcarapi.exceptions.RecordNotFoundException;
import com.ctech.rentcarapi.repositories.CarRepository;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper){
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public List<CarDTO> list(){
        return this.carRepository.findAll()
                .stream()
                .map(carMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CarDTO listById(@NotNull Long id){
        return this.carRepository.findById(id)
                .map(carMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CarDTO create(@Valid @NotNull CarDTO car){
        return this.carMapper.toDTO(this.carRepository.save(this.carMapper.toEntity(car)));
    }

    public CarDTO update(Long id, CarDTO car){
        return this.carRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setBrand(car.brand());
                    recordFound.setModel(car.model());
                    recordFound.setYear(car.year());
                    recordFound.setFuel(car.fuel());
                    recordFound.setCondition(car.condition());
                    return this.carMapper.toDTO(this.carRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(Long id){
        this.carRepository.delete(this.carRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
