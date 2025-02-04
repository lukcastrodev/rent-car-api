package com.ctech.rentcarapi.dtos;

import java.util.Date;

import com.ctech.rentcarapi.models.Car;
import com.ctech.rentcarapi.models.User;

public record ScheduleResponseDTO(
    Long id,
    User user,
    Car car,
    Date rentInitialDate,
    Date rentExpirationDate,
    Date createdAt,
    Date updatedAt
) {
    
}
