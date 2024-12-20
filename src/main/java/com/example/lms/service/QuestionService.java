package com.example.lms.service;

import com.example.lms.dto.QuestionData;

public interface QuestionService {
    QuestionData addQuestion(int courseId, QuestionData questionData);
}
