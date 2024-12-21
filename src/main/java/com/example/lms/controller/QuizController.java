package com.example.lms.controller;

import com.example.lms.dto.QuizData;
import com.example.lms.dto.QuizSubmissionData;
import com.example.lms.model.user_related.User;
import com.example.lms.repository.UserRepo;
import com.example.lms.service.JwtService;
import com.example.lms.service.QuizService;
import com.example.lms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    private final JwtService jwtService;
    private final UserRepo userRepo;

    @PostMapping("/course/{id}/quiz/add")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    public QuizData addQuiz(@PathVariable("id") int id, @RequestBody QuizData quizData) {
        return quizService.addQuiz(id, quizData);
    }

    @PostMapping("/quiz/{id}/submit")
    @PreAuthorize("hasAuthority('STUDENT')")
    public QuizSubmissionData submitQuiz(@PathVariable("id") int id, @RequestBody List<String> answers, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUserName(token);
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()){
            int studentId = user.get().getId();
            return quizService.submitQuiz(studentId, id, answers);
        }
        return null;
    }
}
