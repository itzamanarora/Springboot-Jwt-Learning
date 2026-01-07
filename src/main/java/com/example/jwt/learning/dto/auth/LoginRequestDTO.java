package com.example.jwt.learning.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
public class LoginRequestDTO {

    @NotBlank(message = "User name is required!")
    private String username;

    @NotBlank(message = "Password is required!")
    private String password;
}
