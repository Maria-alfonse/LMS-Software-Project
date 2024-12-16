package com.example.project.service;

import com.example.project.model.User;

public interface UserService {


    void addUser(User user);

    User getUser(int id);
}
