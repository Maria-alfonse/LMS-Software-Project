package com.example.lms.service.impl;

import com.example.lms.model.user_related.Admin;
import com.example.lms.model.user_related.User;
import com.example.lms.repository.AdminRepo;
import com.example.lms.service.JwtService;
import com.example.lms.service.UserService;
import com.example.lms.service.impl.AdminServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Date;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class AdminServiceImplTest {

    @Mock
    private AdminRepo adminRepo;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserService userService;

    AutoCloseable autoCloseable;
    private String token;
    private String invalidToken;
    private final String SECRET_KEY = "KM1sFsOnlHXvd5pNQvth1fjEMwFQGTCmZabS2OyUKmessiisyouruncle=";

    private Admin admin;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        token = Jwts.builder().setSubject("marcelino@mail.com").claim("role", "ADMIN")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 60 * 14))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()).compact();
        invalidToken = "invalid_token";

        admin = new Admin();
        admin.setId(1);
        admin.setName("Marcelino");
        admin.setEmail("marcelino@mail.com");
    }

    @After
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testGetMe() {
        Mockito.when(jwtService.extractUserName(token)).thenReturn("marcelino@mail.com");
        Mockito.when(userService.getUserByEmail("marcelino@mail.com")).thenReturn(admin);
        Mockito.when(adminRepo.findById(admin.getId())).thenReturn(Optional.of(admin));

        Admin fetchedAdmin = adminService.getAdmin(admin.getId());

        assertThat(fetchedAdmin).isNotNull();
        assertThat(fetchedAdmin.getEmail()).isEqualTo("marcelino@mail.com");
        assertThat(fetchedAdmin.getName()).isEqualTo("Marcelino");
    }

    @Test
    void testGetMeInvalidToken() {
        Mockito.when(jwtService.extractUserName(invalidToken)).thenReturn(null);

        User user = userService.getUserByEmail(null);
        assertNull(user);
    }

    @Test
    void testUpdateMe() {
        Admin updatedAdmin = new Admin();
        updatedAdmin.setName("Marcelo");
        updatedAdmin.setPassword("new_password");

        Mockito.when(jwtService.extractUserName(token)).thenReturn("marcelino@mail.com");
        Mockito.when(userService.getUserByEmail("marcelino@mail.com")).thenReturn(admin);
        Mockito.when(adminRepo.findById(admin.getId())).thenReturn(Optional.of(admin));
        Mockito.when(adminRepo.save(Mockito.any(Admin.class))).thenReturn(updatedAdmin);

        adminService.updateAdmin(admin.getId(), updatedAdmin);

        assertThat(updatedAdmin.getName()).isEqualTo("Marcelo");
    }

    @Test
    void addAdmin() {
        Admin adminAdded = new Admin();
        adminAdded.setEmail("abanob@mail.com");
        adminAdded.setName("Abanob");

        Mockito.when(adminRepo.findByEmail(admin.getEmail())).thenReturn(Optional.ofNullable(admin));
        Mockito.when(adminRepo.findByEmail(adminAdded.getEmail())).thenReturn(null);
        Mockito.when(adminRepo.save(adminAdded)).thenReturn(adminAdded);

        Admin newAdmin = adminRepo.save(adminAdded);

        assertThat(newAdmin.getEmail()).isEqualTo("abanob@mail.com");
    }

}
