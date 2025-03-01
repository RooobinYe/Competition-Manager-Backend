package com.demo.competition_manager_backend.controller;

import com.demo.competition_manager_backend.annotation.RequireRole;
import com.demo.competition_manager_backend.model.ApiResponse;
import com.demo.competition_manager_backend.model.User;
import com.demo.competition_manager_backend.service.UserService;
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
