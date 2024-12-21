package com.example.lms.service.impl;

import com.example.lms.dao.request.SignInRequest;
import com.example.lms.dao.request.SignUpRequest;
import com.example.lms.dao.response.JwtAuthenticationResponse;
import com.example.lms.model.user_related.Admin;
import com.example.lms.model.user_related.Role;
import com.example.lms.model.user_related.User;
import com.example.lms.repository.UserRepo;
import com.example.lms.service.AuthenticationService;
import com.example.lms.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request){
        var user = User.builder().email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
        var jwt = jwtService.generateToken(user);
        user.setName(request.getName());
        userRepository.save(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
