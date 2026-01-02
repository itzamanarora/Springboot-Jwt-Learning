package com.example.jwt.learning.service;

import com.example.jwt.learning.entity.User;
import com.example.jwt.learning.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /// Create a new user
    @Transactional
    public User createUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepo.save(user);
    }

    /// Get user by username or all users if username is null or blank
    public List<User> getUser(String username) {

        if(username != null && username.isBlank()) {
            return List.of(userRepo.findByUsername(username).orElseThrow(
//                    () -> new UserPrincipalNotFoundException("User not found with username: " + username)
            ));
        }
        return userRepo.findAll();
    }

    /// Delete user by uuid
    public void deletUserByUuid(String uuid) {
        userRepo.deleteById(uuid);
    }
}
