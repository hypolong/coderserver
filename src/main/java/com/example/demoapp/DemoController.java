package com.example.demoapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String sayHello() {
        return "-------****NEW Hello from CICD Demo App!****";
    }

    @PostMapping("/getuser")
    public String getUser() {
        return "Hello post!";
    }
}