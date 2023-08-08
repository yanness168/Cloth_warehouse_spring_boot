package com.cloth_warehouse.assignment_1.controllers;

import java.util.Arrays;
import java.util.List;

import com.cloth_warehouse.assignment_1.models.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.cloth_warehouse.assignment_1.models.dto.DistributionCenterContext;

@Controller
@RequestMapping("/manage")
@CrossOrigin(origins = "http://localhost:8082")
public class ManagementController {

    private final RestTemplate restTemplate;

    @Autowired
    public ManagementController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public String showManagementPage(@AuthenticationPrincipal User user) {
        if (!user.getRole().contains("ADMIN")) {
            return "access-denied";
        }

        var loggedInUser = user.getUsername();
        System.out.println("Logged in user: " + loggedInUser);

        var centers = restTemplate
                .getForObject("http://localhost:8082/api/distribution-center/centers",
                        DistributionCenterContext[].class);

        System.out.println(Arrays.asList(centers));
        return "manage";
    }

    // Get all distribution centers from api
    @ModelAttribute("distributionCenters")
    public List<DistributionCenterContext> getDistributionCenters() {
        var centers = restTemplate
                .getForObject("http://localhost:8082/api/distribution-center/centers",
                        DistributionCenterContext[].class);
        return Arrays.asList(centers);
    }

}
