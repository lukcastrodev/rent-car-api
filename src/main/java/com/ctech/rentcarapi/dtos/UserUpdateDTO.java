package com.ctech.rentcarapi.dtos;

import org.hibernate.validator.constraints.Length;

public record UserUpdateDTO(
        @Length(min = 3, max = 100) String name,
        @Length(min = 3, max = 100) String lastname,
        @Length(min = 3, max = 100) String nickname
        ) {
}
