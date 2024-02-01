package com.ctech.rentcarapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record CarDTO(
        Long id,

        @NotBlank @NotNull String brand,

        @NotBlank @NotNull String model,

        @NotBlank @NotNull String year,

        @NotBlank @NotNull BigDecimal fuel,

        @NotBlank @NotNull String condition
) {
}
