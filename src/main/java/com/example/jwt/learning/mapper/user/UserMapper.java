package com.example.jwt.learning.mapper.user;

import com.example.jwt.learning.dto.user.CreateUserDTO;
import com.example.jwt.learning.dto.user.UserDto;
import com.example.jwt.learning.entity.user.User;

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
                .uuid(user.getUuid())
                .username(user.getUsername())
                .roles(user.getRoles())
                .createdAt(user.getCreated_at())
                .build();
    }

    public static List<UserDto> userDTOList(List<User> users) {
        return users.stream()
                .map(UserMapper::toDTO)
                .toList();
    }
}
