package com.ctech.rentcarapi.dtos.mappers;

import org.springframework.stereotype.Component;

import com.ctech.rentcarapi.dtos.CarDTO;
import com.ctech.rentcarapi.models.Car;

@Component
public class CarMapper {

    public CarDTO toDTO(Car car){
        if (car == null){
            return null;
        }
        return new CarDTO(car.getId(), car.getBrand(), car.getModel(), car.getYear(), car.getFuel(), car.getCondition());
    }

    public Car toEntity(CarDTO carDTO){
        if(carDTO == null) {
            return null;
        }
        Car car = new Car();
        if(carDTO.id() != null){
            car.setId(carDTO.id());
        }
        car.setBrand(carDTO.brand());
        car.setModel(carDTO.model());
        car.setYear(carDTO.year());
        car.setFuel(carDTO.fuel());
        car.setCondition(carDTO.condition());

        return car;
    }
}
