package com.distributioncenter.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/logout/success";
    }

    @GetMapping("/logout/success")
    public String showLogoutSuccessPage() {
        return "authentication/log_out";
    }
}
