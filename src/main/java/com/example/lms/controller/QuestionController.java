package com.example.lms.controller;

import com.example.lms.dto.QuestionData;
import com.example.lms.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/course/{id}/question/add")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    public QuestionData addQuestion(@PathVariable("id") int id, @RequestBody QuestionData questionData) {
        return questionService.addQuestion(id, questionData);
    }
}
