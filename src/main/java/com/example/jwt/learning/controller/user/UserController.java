package com.example.jwt.learning.controller.user;

import com.example.jwt.learning.dto.user.CreateUserDTO;
import com.example.jwt.learning.dto.user.UserDto;
import com.example.jwt.learning.entity.user.User;
import com.example.jwt.learning.mapper.user.UserMapper;
import com.example.jwt.learning.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "User Management", description = "APIs for creating, managing, and deleting users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/create")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDTO createUserDTO) {
        User user = UserMapper.toEntity(createUserDTO);
        User savedUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserMapper.toDTO(savedUser));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(@RequestParam(required = false) String username) {
        return ResponseEntity.ok(
                userService.getUser(username)
                        .stream()
                        .map(UserMapper::toDTO)
                        .toList()
        );
    }

    @PostMapping("/users/make-admin/{uuid}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Promote User to Admin")
    public ResponseEntity<UserDto> makeUserAdmin(@PathVariable String uuid){
        User updatedUser = userService.makeUserAdmin(uuid);
        return ResponseEntity.ok(UserMapper.toDTO(updatedUser));
    }

    @DeleteMapping("/admin/users/{uuid}")
    public ResponseEntity<Void> deleteUser(@PathVariable String uuid) {
        userService.deletUserByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
