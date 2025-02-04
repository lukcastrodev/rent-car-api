package com.ctech.rentcarapi.dtos.mappers;

import org.springframework.stereotype.Component;

import com.ctech.rentcarapi.dtos.ScheduleResponseDTO;
import com.ctech.rentcarapi.models.Schedule;

@Component
public class ScheduleMapper {
     
    public ScheduleResponseDTO toDTO(Schedule schedule){
        if (schedule == null){
            return null;
        }
        return new ScheduleResponseDTO(schedule.getId(),
                schedule.getUser(), 
                schedule.getCar(), 
                schedule.getRentInitialDate(), 
                schedule.getRentExpirationDate(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt());
    }
}
