package com.example.lms.controller;

import com.example.lms.model.user_related.Instructor;
import com.example.lms.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;

    @PostMapping("/add/instructor")
    public Instructor addInstructor(@RequestBody Instructor instructor){
        return instructorService.addInstructor(instructor);
    }

    @PostMapping("/delete/instructor/{id}")
    public void deleteInstructor(@PathVariable long id) {
        instructorService.deleteInstructor(id);
    }

    @PostMapping("/update/instructor/{id}")
    public void updateInstructor(@PathVariable long id, @RequestBody Instructor instructor) {
        instructorService.updateInstructor(id, instructor);
    }

    @GetMapping("/show/instructors")
    public List<Instructor> getInstructors(){
        return instructorService.getAll();
    }
}
