package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    // login and view/update user's profile functionality.

    private final UserService userService;

    @GetMapping("/login/{id}")
    public User login(@PathVariable("id") int id) {
        return userService.getUser(id);
    }
}
