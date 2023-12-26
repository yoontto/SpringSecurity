package com.spring.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/index")
    public ResponseEntity<String> hello(Principal principal) {
        return ResponseEntity.ok("Welcome to my page : " + principal.getName());
    }
}
