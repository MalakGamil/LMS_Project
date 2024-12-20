package com.example.demo.repository;

import com.example.demo.model.Question;
import com.example.demo.model.QuestionBank;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QuestionBankRepository {
    private final Map<String, QuestionBank> questionBanks = new HashMap<>();

    public QuestionBank findByCourseId(String courseId) {
        return questionBanks.get(courseId);
    }

    public void addQuestionBank(String courseId, QuestionBank questionBank) {
        questionBanks.put(courseId, questionBank);
    }
}
