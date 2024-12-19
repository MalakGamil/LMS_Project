package com.example.demo.repository;

import com.example.demo.model.Course;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CourseRepository {

    private final Map<String, Course> courseStorage = new HashMap<>();

    public void save(Course course) {
        courseStorage.put(course.getId(), course);
    }

    public Optional<Course> findById(String id) {
        return Optional.ofNullable(courseStorage.get(id));
    }

    public Map<String, Course> findAll() {
        return courseStorage;
    }
}