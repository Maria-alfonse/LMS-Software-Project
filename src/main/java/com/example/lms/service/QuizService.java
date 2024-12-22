package com.example.lms.service;

import com.example.lms.dto.QuizData;
import com.example.lms.dto.QuizSubmissionData;
import com.example.lms.model.course_related.quiz_related.QuizSubmission;

import java.util.List;

public interface QuizService {
    QuizData addQuiz(int courseId, QuizData quizData);

    QuizSubmissionData submitQuiz(int studentId, int quizId, List<String> answers);

    List<QuizSubmission> getSubmissions(int id);
}
