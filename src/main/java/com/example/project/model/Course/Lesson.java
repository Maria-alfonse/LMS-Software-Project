package com.example.project.model.Course;

import com.example.project.model.Users.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import com.example.project.model.Question.Question;
import lombok.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name = "Lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @OneToMany(mappedBy = "Lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> attendance = new ArrayList<>();

    private int OTP;
}