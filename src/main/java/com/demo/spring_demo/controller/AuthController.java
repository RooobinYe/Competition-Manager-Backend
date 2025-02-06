package com.demo.spring_demo.controller;

import com.demo.spring_demo.model.dto.LoginRequest;
import com.demo.spring_demo.model.dto.LoginResponse;
import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
} 