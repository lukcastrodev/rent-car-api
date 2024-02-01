package com.ctech.rentcarapi.dtos.mappers;

import com.ctech.rentcarapi.dtos.UserDTO;
import com.ctech.rentcarapi.models.User;
import com.ctech.rentcarapi.utils.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user){
        if (user == null){
            return null;
        }
        return new UserDTO(user.getId(), user.getName(), user.getLastname(), user.getNickname(), user.getPassword());
    }

    public User toEntity(UserDTO userDTO){
        if(userDTO == null) {
            return null;
        }
        User user = new User();
        if(userDTO.id() != null){
            user.setId(userDTO.id());
        }
        
        user.setName(userDTO.name());
        user.setLastname(userDTO.lastname());
        user.setNickname(userDTO.nickname());
       
        String password = PasswordEncoder.encoder(userDTO.password());
        user.setPassword(password);
      

        return user;
    }
}
