package com.andersenlab.aadamovich.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping(value = "/main")
    public String forwardToLoginPage() {
        return "main";
    }

    @GetMapping(value = "/admin")
    public String forwardToAdminPage() {
        return "admin_page";
    }

    @GetMapping(value = "/grand_user")
    public String forwardToGrandUserPage() {
        return "grand_user_page";
    }
}
