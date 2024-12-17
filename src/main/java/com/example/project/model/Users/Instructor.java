package com.example.project.model.Users;

import jakarta.persistence.*;
import com.example.project.model.Question.Question;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Instructor")
public class Instructor extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
}
