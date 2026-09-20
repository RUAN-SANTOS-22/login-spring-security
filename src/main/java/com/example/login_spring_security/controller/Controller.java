package com.example.login_spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/login")
    public String login() {
        return "Usuario encontrado";
    }

    @GetMapping("/home")
    public String home() {
        return "Home";
    }

}
