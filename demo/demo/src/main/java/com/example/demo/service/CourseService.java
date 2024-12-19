package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public void createCourse(Course course) {
        courseRepository.save(course);
    }

    public Course getCourseById(String id) {
        return courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Course not found"));
    }

    public void enrollStudent(String courseId, String studentId) {
        Course course = getCourseById(courseId);
        course.getEnrolledStudents().add(studentId);
        courseRepository.save(course);
    }
    public List<Course> getAllCourses() {
        return courseRepository.findAll().values().stream().collect(Collectors.toList());
    }


}
