package com.example.lms.service.impl;

import com.example.lms.model.user_related.Admin;
import com.example.lms.model.user_related.User;
import com.example.lms.repository.AdminRepo;
import com.example.lms.service.AdminService;
import com.example.lms.service.JwtService;
import com.example.lms.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceImplTest {

    @Mock
    private AdminRepo adminRepo;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private JwtService jwtService;

    AutoCloseable autoCloseable;
    private String token;
    private String invalidToken;
    private String SECRET_KEY = "KM1sFsOnlHXvd5pNQvth1fjEMwFQGTCmZabS2OyUKmessiisyouruncle=";

    UserService userService;

    Admin adminToAdd;
    Admin adminAdded;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        token = Jwts.builder().setSubject("marcelino@mail.com").claim("role", "ADMIN")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 60 * 14))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()).compact();
        invalidToken = "blabla";

        adminToAdd = new Admin();
        adminToAdd.setName("Marcelino");
        adminToAdd.setEmail("marcelino@mail.com");
    }

    @After
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void addAdmin() {
        adminAdded = new Admin();
        adminAdded.setEmail("abanob@mail.com");
        adminAdded.setName("Abanob");

        Mockito.when(adminRepo.findByEmail(adminToAdd.getEmail())).thenReturn(Optional.ofNullable(adminToAdd));
        Mockito.when(adminRepo.findByEmail(adminAdded.getEmail())).thenReturn(null);
        Mockito.when(adminRepo.save(adminAdded)).thenReturn(adminAdded);

        Admin newAdmin = adminRepo.save(adminAdded);

        assertThat(newAdmin.getEmail()).isEqualTo("abanob@mail.com");
    }

    @Test
    void setUserRole(){

    }
}