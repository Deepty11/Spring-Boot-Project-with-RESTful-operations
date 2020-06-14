package com.example.project.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//for customizing white label while requesting "localhost:8080 " in browser

import java.util.Date;
@RestController
public class Home {
    @GetMapping
    public String home(){
        return "Application is working "+new Date();

    }
}
