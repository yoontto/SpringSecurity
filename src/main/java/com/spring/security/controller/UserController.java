package com.spring.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {


    @GetMapping("/get")
    public ResponseEntity<String> getAccount(Principal principal) {
        return ResponseEntity.ok("Welcome back user : " + principal.getName());
    }
}
