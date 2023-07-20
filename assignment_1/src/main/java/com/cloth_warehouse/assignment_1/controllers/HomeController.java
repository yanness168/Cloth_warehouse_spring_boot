package com.cloth_warehouse.assignment_1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String greeting() {
        return "home";
    }
}
