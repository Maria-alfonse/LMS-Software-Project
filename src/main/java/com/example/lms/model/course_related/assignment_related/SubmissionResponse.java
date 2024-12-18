package com.example.lms.model.course_related.assignment_related;

import com.example.lms.model.course_related.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SubmissionResponse {
    private int submission_id;
    private int student_id;
    private int grade;
    private boolean marked;
    private FileEntity fileEntity;

    public SubmissionResponse(AssignmentSubmission as) {
        this.submission_id = as.getId();
        this.student_id = as.getStudent().getId();
        this.grade = as.getGrade();
        this.marked = as.isMarked();
        this.fileEntity = as.getFileEntity();
    }
}
