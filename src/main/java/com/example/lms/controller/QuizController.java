package com.example.lms.controller;

import com.example.lms.model.course_related.Quiz;
import com.example.lms.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/quiz")
@RestController
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PostMapping("/create")
    public QuizData createQuiz(@RequestBody Quiz quiz){
        return quizService.createQuiz(quiz);
    }

    @PostMapping("/submit/{id}")
    public int gradeQuiz(@PathVariable("id") int id, @RequestBody List<String> answers){
        return quizService.gradeQuiz(2, id, answers);
    }
}
