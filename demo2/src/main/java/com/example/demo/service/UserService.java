package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    public void registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already in use.");
        }
        userRepository.save(user);
    }

    // Authenticate user for login
    public User authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // Get all users
    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    // Delete a user
    public void deleteUser(String id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found.");
        }
        userRepository.delete(id);
    }

    // Update user role
    public void updateUserRole(String id, User.Role role) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found.");
        }
        user.setRole(role);
        userRepository.save(user);
    }
}
