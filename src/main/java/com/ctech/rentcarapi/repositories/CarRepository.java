package com.ctech.rentcarapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctech.rentcarapi.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

}
