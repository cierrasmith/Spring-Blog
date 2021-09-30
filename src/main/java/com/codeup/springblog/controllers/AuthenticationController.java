package com.codeup.springblog.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }
}
