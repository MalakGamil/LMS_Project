package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Lesson;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    private static final String MEDIA_DIR = "media/";

    // Check role for instructor
    private void validateInstructorRole(String role) {
        if (!"instructor".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only instructors can perform this action.");
        }
    }

    // Check role for student
    private void validateStudentRole(String role) {
        if (!"student".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only students can perform this action.");
        }
    }

    // Create a new course (Instructor only)
    public void createCourse(Course course, String role) {
        validateInstructorRole(role);
        courseRepository.save(course);
    }

    // Get a course by ID
    public Course getCourseById(String id) {
        return courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Course not found"));
    }

    // Enroll a student in a course (Instructor only)
    public void enrollStudent(String courseId, String studentId, String role) {
        validateInstructorRole(role);
        Course course = getCourseById(courseId);
        if (!course.getEnrolledStudents().contains(studentId)) {
            course.addStudent(studentId);
            courseRepository.save(course);
        }
    }

    // Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll().values().stream().collect(Collectors.toList());
    }

    // Generate OTP for a lesson (Instructor only)
    public String generateOtpForLesson(String courseId, String lessonId, String role) {
        validateInstructorRole(role);
        Course course = getCourseById(courseId);
        Optional<Lesson> lessonOptional = course.getLessons().stream()
                .filter(lesson -> lesson.getLessonId().equals(lessonId))
                .findFirst();

        if (lessonOptional.isPresent()) {
            String otp = generateRandomOtp();
            Lesson lesson = lessonOptional.get();
            lesson.setOtp(otp);
            lesson.setOtpGeneratedTime(System.currentTimeMillis());
            courseRepository.save(course);
            return otp;
        }
        throw new IllegalArgumentException("Lesson not found");
    }

    // Validate OTP for attendance (Student only)
    public boolean validateOtpForAttendance(String courseId, String lessonId, String studentId, String enteredOtp, String role) {
        validateStudentRole(role);
        Course course = getCourseById(courseId);
        Optional<Lesson> lessonOptional = course.getLessons().stream()
                .filter(lesson -> lesson.getLessonId().equals(lessonId))
                .findFirst();

        if (lessonOptional.isPresent()) {
            Lesson lesson = lessonOptional.get();

            if (lesson.isOtpExpired()) {
                return false;
            }

            if (lesson.hasExceededOtpAttempts(studentId)) {
                return false;
            }

            if (lesson.getOtp().equals(enteredOtp)) {
                lesson.markAttendance(studentId);
                courseRepository.save(course);
                return true;
            } else {
                lesson.addOtpAttempt(studentId);
                courseRepository.save(course);
                return false;
            }
        }
        throw new IllegalArgumentException("Lesson not found");
    }

    // Add a media file to a course (Instructor only)
    public void addMediaFile(String courseId, String mediaUrl, String role) throws IOException {
        validateInstructorRole(role);

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        String savedMediaPath = saveMediaFile(mediaUrl);

        course.addMediaFile(savedMediaPath);
        courseRepository.save(course);
    }

    // Save media file (Helper method)
    public String saveMediaFile(String mediaUrl) throws IOException {
        File directory = new File(MEDIA_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String uniqueMediaName = UUID.randomUUID().toString() + "-" + Paths.get(mediaUrl).getFileName().toString();
        Path mediaPath = Paths.get(MEDIA_DIR + uniqueMediaName);

        Files.copy(Paths.get(mediaUrl), mediaPath);

        return mediaPath.toString();
    }

    private String generateRandomOtp() {
        int otp = (int) (Math.random() * 1000000);
        return String.format("%06d", otp); // Format OTP to be 6 digits
    }
    // Update course details (Instructor only)
    public void updateCourse(String id, Course updatedCourse, String role) {
        validateInstructorRole(role);
        Course existingCourse = getCourseById(id);
        existingCourse.setTitle(updatedCourse.getTitle());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setDuration(updatedCourse.getDuration());
        courseRepository.save(existingCourse);
    }

    // Delete a course by ID (Instructor only)
    public void deleteCourse(String id, String role) {
        validateInstructorRole(role);
        if (!courseRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Course not found");
        }
        courseRepository.delete(id);
    }

}
