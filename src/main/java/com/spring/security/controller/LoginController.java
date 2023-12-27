package com.spring.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login/success")
    public String success() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!ObjectUtils.isEmpty(principal)){
            return "main";
        }
        return "403.html";
    }
}
