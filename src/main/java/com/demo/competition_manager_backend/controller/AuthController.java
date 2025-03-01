package com.demo.competition_manager_backend.controller;

import com.demo.competition_manager_backend.model.dto.LoginRequest;
import com.demo.competition_manager_backend.model.dto.LoginResponse;
import com.demo.competition_manager_backend.model.ApiResponse;
import com.demo.competition_manager_backend.service.UserService;
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