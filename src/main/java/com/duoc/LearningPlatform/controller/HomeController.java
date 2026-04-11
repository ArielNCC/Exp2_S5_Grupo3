package com.duoc.LearningPlatform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String saludo() {
        return "LearningPlatform API version 1.0";
    }
}
