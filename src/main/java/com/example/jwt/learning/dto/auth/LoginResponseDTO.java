package com.example.jwt.learning.dto.auth;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private String tokenType;
    private String token;
}
