package com.ctech.rentcarapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctech.rentcarapi.models.Car;
import com.ctech.rentcarapi.models.User;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
    public List<Car> findByUsers(User user);
}
