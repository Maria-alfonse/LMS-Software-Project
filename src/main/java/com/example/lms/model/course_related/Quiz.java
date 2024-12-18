package com.example.lms.model.course_related;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


}
