package com.example.demo.controller;

import com.example.demo.model.Performance;
import com.example.demo.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/performance")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @GetMapping
    public Performance getPerformance(@RequestParam String userId, @RequestParam String courseId) {
        return performanceService.getPerformance(userId, courseId);
    }
}
