package com.ctech.rentcarapi.services;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ctech.rentcarapi.dtos.UserDTO;
import com.ctech.rentcarapi.dtos.mappers.UserMapper;
import com.ctech.rentcarapi.exceptions.RecordNotFoundException;
import com.ctech.rentcarapi.repositories.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
   
    public UserService(UserRepository userRepository, 
            UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> list(){
        return this.userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO listById(@NotNull Long id) {
        return this.userRepository.findById(id).map(userMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public UserDTO create(@Valid @NotNull UserDTO user){
        return this.userMapper.toDTO(this.userRepository.save(this.userMapper.toEntity(user)));
    }

    public UserDTO update(Long id,
                           UserDTO user){
        return this.userRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(user.name());
                    recordFound.setLastname(user.lastname());
                    recordFound.setNickname(user.nickname());
                    return this.userMapper.toDTO(this.userRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull Long id) {
        this.userRepository.delete(this.userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
