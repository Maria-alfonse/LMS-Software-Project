package com.example.lms.service;


import com.example.lms.dao.request.SignInRequest;
import com.example.lms.dao.request.SignUpRequest;
import com.example.lms.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
    JwtAuthenticationResponse signUp(SignUpRequest signUpRequest);
}
