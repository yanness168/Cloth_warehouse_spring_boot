package com.cloth_warehouse.assignment_1.controllers;

import com.cloth_warehouse.assignment_1.models.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class ManagementController {
    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping
    public String showManagementPage(@AuthenticationPrincipal User user) {
        if (!user.getRole().contains("EMPLOYEE")) {
            return "access-denied";
        }

        var loggedInUser = user.getUsername();
        System.out.println("Logged in user: " + loggedInUser);
        return "manage";
    }
}
