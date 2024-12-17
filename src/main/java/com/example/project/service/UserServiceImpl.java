package com.example.project.service;

import com.example.project.model.Users.User;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;


    @Override
    public void addUser(User user) {
        User newUser = userRepo.save(user);
    }

    @Override
    public User getUser(int id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        return null;
    }

}