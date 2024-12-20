package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CourseMaterialRepository {

    // Map to store course materials: courseId -> List of materials
    private static final Map<String, List<String>> courseMaterials = new HashMap<>();

    // Add material to a course
    public static void addMaterial(String courseId, String material) {
        courseMaterials.computeIfAbsent(courseId, k -> new ArrayList<>()).add(material);
    }

    // Get materials for a course
    public List<String> getMaterials(String courseId) {
        return courseMaterials.getOrDefault(courseId, new ArrayList<>());
    }
}
