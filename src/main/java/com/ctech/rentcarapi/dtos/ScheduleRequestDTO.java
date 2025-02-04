package com.ctech.rentcarapi.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public record ScheduleRequestDTO(
    @NotNull Long userId,
    @NotNull Long carId,
    @NotNull @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd") Date rentInitialDate,
    @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd") Date rentExpirationDate
) {
    
}
