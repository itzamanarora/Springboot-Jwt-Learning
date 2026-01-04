package com.example.jwt.learning.service;

import com.example.jwt.learning.entity.Role;
import com.example.jwt.learning.entity.User;
import com.example.jwt.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /// Create a new user
    @Transactional
    public User createUser(User user) {
        user.setRoles(Set.of(Role.ROLE_USER));
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Transactional
    public User makeUserAdmin(String uuid) {
        User user = userRepository.findById(uuid).orElseThrow(
                () -> new UsernameNotFoundException("User not found with uuid: " + uuid)
        );
        Set<Role> roles = user.getRoles();
        roles.add(Role.ROLE_ADMIN);
        user.setRoles(roles);
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
