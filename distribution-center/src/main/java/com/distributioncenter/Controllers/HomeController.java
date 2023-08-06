package com.distributioncenter.Controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    @GetMapping("/")
    public String redirectToHomePage() {
        return "redirect:/homepage";
    }
    @GetMapping("/homepage")
    public String showHomePage() {
        return "homepage";
    }
}
