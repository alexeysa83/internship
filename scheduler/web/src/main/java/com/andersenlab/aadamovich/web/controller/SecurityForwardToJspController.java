package com.andersenlab.aadamovich.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityForwardToJspController {

    @GetMapping(value = "/login")
    public String forwardToLoginPage() {
        return "login_form";
    }

    @GetMapping(value = "/errors/403")
    public String forwardToAccessDeniedPage() {
        return "access_denied";
    }
}
