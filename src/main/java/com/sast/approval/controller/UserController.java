package com.sast.approval.controller;

import com.sast.approval.annotation.RequireRole;
import com.sast.approval.model.ApiResponse;
import com.sast.approval.model.User;
import com.sast.approval.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequireRole({2, 3})  // 只允许 AcademyAdmin(2) 和 SuperAdmin(3) 访问
    @PostMapping("/user")
    public ApiResponse<Object> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
