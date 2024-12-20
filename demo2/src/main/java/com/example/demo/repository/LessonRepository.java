package com.example.demo.repository;

import com.example.demo.model.Lesson;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LessonRepository {

    private final Map<String, Map<String, Lesson>> courseLessons = new HashMap<>();

    //  save or update a lesson in the repository
    public void save(String courseId, Lesson lesson) {
        courseLessons.putIfAbsent(courseId, new HashMap<>());
        courseLessons.get(courseId).put(lesson.getLessonId(), lesson);
    }

    // retrieve a lesson by courseId and lessonId
    public Lesson findLessonByCourseAndLessonId(String courseId, String lessonId) {
        if (courseLessons.containsKey(courseId)) {
            return courseLessons.get(courseId).get(lessonId);
        }
        return null;
    }

    // retrieve all lessons for a course
    public Map<String, Lesson> findAllLessonsByCourseId(String courseId) {
        return courseLessons.getOrDefault(courseId, new HashMap<>());
    }

    //retrieve all lessons (optional if needed)
    public Map<String, Map<String, Lesson>> findAll() {
        return courseLessons;
    }

    // delete a lesson by courseId and lessonId
    public void delete(String courseId, String lessonId) {
        if (courseLessons.containsKey(courseId)) {
            Map<String, Lesson> lessons = courseLessons.get(courseId);
            if (lessons.containsKey(lessonId)) {
                lessons.remove(lessonId);  // Remove the lesson by lessonId
            }
        }
    }
}
