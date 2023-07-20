package com.cloth_warehouse.assignment_1.models.form;

import com.cloth_warehouse.assignment_1.models.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;

    private String role;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(role)
                .build();
    }
}