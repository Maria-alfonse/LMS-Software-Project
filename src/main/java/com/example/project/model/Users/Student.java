package com.example.project.model.Users;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.*;

@Data
@Entity
@Table(name = "Student")
public class Student extends User {
    private int grade;

    @ElementCollection
    private List<Integer> registeredCourses;
}
