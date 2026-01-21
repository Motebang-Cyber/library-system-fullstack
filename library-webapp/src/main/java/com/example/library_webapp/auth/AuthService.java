package com.example.library_webapp.auth;

import com.example.library_webapp.model.Role;
import com.example.library_webapp.model.User;
import com.example.library_webapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.library_webapp.auth.dto.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest req) {
        if (userRepo.findByUsername(req.getUsername()).isPresent())
            throw new RuntimeException("Username exists");

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setRole(req.getRole() == null ? Role.MEMBER : req.getRole());

        userRepo.save(user);
    }

    public AuthResponse login(LoginRequest req) {
        User user = userRepo.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!encoder.matches(req.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid credentials");

        return new AuthResponse(
                jwtUtil.generateToken(user.getUsername(), user.getRole())
        );
    }


}
