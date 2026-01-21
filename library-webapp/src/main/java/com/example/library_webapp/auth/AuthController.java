package com.example.library_webapp.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.library_webapp.auth.dto.*;
/**
 * Controller to handle user registration and login.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest r) {
        service.register(r);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest r) {
        return service.login(r);
    }
}
