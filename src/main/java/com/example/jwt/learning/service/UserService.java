package com.example.jwt.learning.service;

import com.example.jwt.learning.entity.User;
import com.example.jwt.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /// Create a new user
    @Transactional
    public User createUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    /// Get user by username or all users if username is null or blank
    public List<User> getUser(String username) throws UsernameNotFoundException {
        if(username != null && !username.isBlank()) {
            return List.of(userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username))
            );
        }
        return userRepository.findAll();
    }

    /// Delete user by uuid
    public void deletUserByUuid(String uuid) {
        userRepository.deleteById(uuid);
    }
}
