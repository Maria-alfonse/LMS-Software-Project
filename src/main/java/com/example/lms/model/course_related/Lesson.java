package com.example.lms.model.course_related;

import com.example.lms.model.user_related.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "Lesson")
    private List<Student> attendance = new ArrayList<>();

    private int OTP;

}
