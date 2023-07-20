package com.cloth_warehouse.assignment_1.controllers;

import com.cloth_warehouse.assignment_1.models.form.RegistrationForm;
import com.cloth_warehouse.assignment_1.models.User;
import com.cloth_warehouse.assignment_1.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping
    public String registerUserAccount(RegistrationForm form) {
        User user = form.toUser(passwordEncoder);
        userRepository.save(user);
        return "redirect:/login";
    }
}
