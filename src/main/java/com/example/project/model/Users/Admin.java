package com.example.project.model.Users;

import jakarta.persistence.*;
import com.example.project.model.Question.Question;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "Admin")
public class Admin extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
}