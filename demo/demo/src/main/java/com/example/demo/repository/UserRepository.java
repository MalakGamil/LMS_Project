package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private final Map<String, User> userStorage = new HashMap<>();

    public void save(User user) {
        userStorage.put(user.getId(), user);
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(userStorage.get(id));
    }

    public Map<String, User> findAll() {
        return userStorage;
    }
}