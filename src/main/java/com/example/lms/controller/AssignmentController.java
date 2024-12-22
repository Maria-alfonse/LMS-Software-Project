package com.example.lms.controller;

import com.example.lms.model.course_related.assignment_related.Assignment;
import com.example.lms.dto.SubmissionResponse;
import com.example.lms.model.user_related.User;
import com.example.lms.service.AssignmentService;
import com.example.lms.service.JwtService;
import com.example.lms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;

    private final JwtService jwtService;

    private final UserService userService;

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PostMapping("/course/{id}/assignment/add")
    public Assignment addAssignment(@RequestBody Assignment assignment, @PathVariable int id) {
        return assignmentService.addAssignment(id, assignment);
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/assignment/{id}/submit")
    public SubmissionResponse submitAssignment(@PathVariable("id") int assignmentId, @RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUserName(token);
        User user = userService.getUserByEmail(email);
        if (user == null)
            return null;
        return assignmentService.submitAssignment(user.getId(), assignmentId, file);
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @GetMapping("/assignment/{id}/submissions")
    public List<SubmissionResponse> getSubmissions(@PathVariable("id") int id) {
        return assignmentService.getSubmissions(id);
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PatchMapping("/submission/{id}/grade")
    public SubmissionResponse gradeSubmission(@PathVariable("id") int submissionId, @RequestBody int grade) {
        return assignmentService.gradeSubmission(submissionId, grade);
    }

}
