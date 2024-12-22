package com.example.lms.service;

import com.example.lms.model.course_related.assignment_related.Assignment;
import com.example.lms.dto.SubmissionResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AssignmentService {
    Assignment addAssignment(int courseId, Assignment assignment);

    SubmissionResponse submitAssignment(int studentId, int assignmentId, MultipartFile file) throws IOException;

    List<SubmissionResponse> getSubmissions(int id);

    SubmissionResponse gradeSubmission(int submissionId, int grade);
}
