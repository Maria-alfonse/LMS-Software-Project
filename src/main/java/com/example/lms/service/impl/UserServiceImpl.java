package com.example.lms.service.impl;

import com.example.lms.model.user_related.User;
import com.example.lms.repository.UserRepo;
import com.example.lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                System.out.println(email);
                return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public User getUser(int id){
        Optional<com.example.lms.model.user_related.User> user = userRepo.findById(id);
        return user.orElse(null);
    }

}
