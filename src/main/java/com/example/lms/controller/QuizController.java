package com.example.lms.controller;

import com.example.lms.dto.QuizData;
import com.example.lms.dto.QuizSubmissionData;
import com.example.lms.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/course/{id}/quiz/add")
    public QuizData addQuiz(@PathVariable("id") int id, @RequestBody QuizData quizData) {
        return quizService.addQuiz(id, quizData);
    }

    @PostMapping("/quiz/{id}/submit")
    public QuizSubmissionData submitQuiz(@PathVariable("id") int id, @RequestBody List<String> answers) {
        return quizService.submitQuiz(53, id, answers);
    }
}
