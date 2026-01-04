package com.example.jwt.learning.controller;

import com.example.jwt.learning.dto.CreateUserDTO;
import com.example.jwt.learning.dto.UserDto;
import com.example.jwt.learning.entity.User;
import com.example.jwt.learning.mapper.UserMapper;
import com.example.jwt.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDTO createUserDTO) {
        User user = UserMapper.toEntity(createUserDTO);
        User savedUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserMapper.toDTO(savedUser));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(@RequestParam(required = false) String username) {
        return ResponseEntity.ok(
                userService.getUser(username)
                        .stream()
                        .map(UserMapper::toDTO)
                        .toList()
        );
    }
}
