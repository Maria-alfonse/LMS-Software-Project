package com.example.project.model.Course;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Lesson {
    private final int id;

    private final List<Integer> attendance = new ArrayList<>();

    private int OTP;
}
