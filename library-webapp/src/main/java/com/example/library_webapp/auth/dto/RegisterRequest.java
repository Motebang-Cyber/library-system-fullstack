package com.example.library_webapp.auth.dto;

import lombok.Data;
import com.example.library_webapp.model.Role;
@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}
