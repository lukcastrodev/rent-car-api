package com.ctech.rentcarapi.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public record RentCarDTO(
    @NotNull Long userId,
    @NotNull Long carId,
    @NotNull @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd") Date rentDate,
    @NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd") Date expirationDate
) {
    
}
