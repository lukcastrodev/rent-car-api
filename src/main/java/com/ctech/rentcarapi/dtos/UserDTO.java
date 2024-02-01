package com.ctech.rentcarapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UserDTO(
        Long id,
        @NotBlank @NotNull @Length(min = 3, max = 100) String name,
        @NotBlank @NotNull @Length(min = 3, max = 100) String lastname,
        @NotBlank @NotNull @Length(min = 3, max = 100) String nickname,
        @NotBlank @NotNull String password
        ) {
}
