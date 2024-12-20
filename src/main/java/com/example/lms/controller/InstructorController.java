package com.example.lms.controller;

import com.example.lms.model.user_related.Admin;
import com.example.lms.model.user_related.Instructor;
import com.example.lms.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructor")
public class InstructorController {
    private final InstructorService instructorService;

    @PostMapping("/add")
    public Instructor addInstructor(@RequestBody Instructor instructor) {
        return instructorService.addInstructor(instructor);
    }
    @PostMapping("/delete/{id}")
    public void deleteInstructor(@PathVariable Integer id) {
        instructorService.deleteInstructor(id);
    }
    @PostMapping("/update/{id}")
    public void updateInstructor(@PathVariable Integer id, @RequestBody Instructor instructor) {
        instructorService.updateInstructor(id, instructor);
    }
    @GetMapping("/get/{id}")
    public Instructor getInstructor(@PathVariable Integer id) {
        return instructorService.getInstructor(id);
    }

    @GetMapping
    public List<Instructor> getInstructors() {
        return instructorService.getAll();
    }
}
