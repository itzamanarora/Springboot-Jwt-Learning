package com.example.jwt.learning.dto.user;

import com.example.jwt.learning.entity.user.Role;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.Set;

@Data
@Builder
public class UserDto {
    private String uuid;
    private String username;
    private Set<Role> roles;
    private Date createdAt;
}
