package com.distributioncenter.Controllers;

import com.distributioncenter.Models.User;
import com.distributioncenter.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String showLoginForm() {
        return "authentication/login";
    }

    @PostMapping
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        // Retrieve the user from the database based on the entered username
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Credentials match, redirect to the home page
            return "redirect:/";
        } else {
            // Credentials don't match, display error message
            model.addAttribute("error", "Unable to login. Check your username and password.");
            return "authentication/login";
        }
    }
}
