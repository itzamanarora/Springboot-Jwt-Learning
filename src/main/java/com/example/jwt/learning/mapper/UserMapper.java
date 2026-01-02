package com.example.jwt.learning.mapper;

import com.example.jwt.learning.dto.CreateUserDTO;
import com.example.jwt.learning.dto.UserDto;
import com.example.jwt.learning.entity.User;

import java.util.List;

public class UserMapper {

    public static User toEntity(CreateUserDTO createUserDTO) {
        User user = new User();
        user.setUsername(createUserDTO.getUsername());
        user.setPassword(createUserDTO.getPassword());
        return user;
    }

    public static UserDto toDTO(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .createdAt(user.getCreated_at())
                .build();
    }

    public static List<UserDto> userDTOList(List<User> users) {
        return users.stream()
                .map(UserMapper::toDTO)
                .toList();
    }
}
