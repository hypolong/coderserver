package com.example.demoapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController2 {

    @GetMapping("/hello2")
    public String sayHello() {
        return "2222-------****NEW Hello from CICD Demo App!****";
    }

    @PostMapping("/getuser2")
    public String getUser() {
        return "2222Hello post!";
    }
}