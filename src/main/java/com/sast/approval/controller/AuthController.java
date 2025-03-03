package com.sast.approval.controller;

import com.sast.approval.model.dto.LoginRequest;
import com.sast.approval.model.dto.LoginResponse;
import com.sast.approval.model.ApiResponse;
import com.sast.approval.service.UserService;
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