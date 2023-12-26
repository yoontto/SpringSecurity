package com.spring.security.controller;

import com.spring.security.request.UserDTO;
import com.spring.security.response.BaseResponse;
import com.spring.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    @PostMapping("/register-account")
    public ResponseEntity<BaseResponse> registerAccount(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.registerAccount(userDTO));
    }
}
