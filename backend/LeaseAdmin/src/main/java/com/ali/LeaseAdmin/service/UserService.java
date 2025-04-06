package com.ali.LeaseAdmin.service;

import com.ali.LeaseAdmin.model.AppUser;
import com.ali.LeaseAdmin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AppUser registerUser(AppUser user) {
        // Check if username already exists
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("Username already exists");
        }
        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Set default role if none provided
        if(user.getRoles() == null || user.getRoles().isEmpty()){
            user.setRoles("ROLE_USER");
        }
        return userRepository.save(user);
    }
}
