package com.example.demo.service;

import com.example.demo.model.Performance;
import org.springframework.stereotype.Service;

@Service
public class PerformanceService {

    public Performance getPerformance(String userId, String courseId) {
        // Logic to calculate performance (stubbed for simplicity)
        Performance performance = new Performance();
        performance.setUserId(userId);
        performance.setCourseId(courseId);
        performance.setAttendancePercentage(90);
        performance.setScore(85);
        return performance;
    }
}