package com.sm.ems.security.controller;

import com.sm.ems.security.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

    @RequestMapping("/login")
    public String login() {
        return "security/login";
    }

    @RequestMapping("/register")
    public String register(User user) {
        return "security/register";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @RequestMapping("/index")
    public String homePage() {
        return "/index2";
    }
}