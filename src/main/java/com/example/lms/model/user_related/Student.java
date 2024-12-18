package com.example.lms.model.user_related;

import com.example.lms.model.course_related.assignment_related.AssignmentSubmission;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Student extends User{
    @OneToMany(mappedBy = "student")
    private List<AssignmentSubmission> submissions = new ArrayList<>();


}
