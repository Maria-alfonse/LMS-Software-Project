package com.example.project.model.Users;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Student extends User {
    private int grade;
    private List<Integer> registeredCourses;
}
