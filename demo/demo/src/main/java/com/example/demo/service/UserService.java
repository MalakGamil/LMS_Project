package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        userRepository.save(user);
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public void updateUser(String id, User updatedUser) {
        User user = getUserById(id);
        user.setName(updatedUser.getName());
        user.setRole(updatedUser.getRole());
        userRepository.save(user);
    }
}