package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private final Map<String, User> users = new HashMap<>(); // In-memory storage

    // Save a user
    public void save(User user) {
        users.put(user.getId(), user);
    }

    // Find a user by ID
    public User findById(String id) {
        return users.get(id);
    }

    // Find a user by email
    public User findByEmail(String email) {
        return users.values()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    // Get all users
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    // Delete a user
    public void delete(String id) {
        users.remove(id);
    }
}
