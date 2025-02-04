package com.demo.spring_demo.controller;

import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.model.User;
import com.demo.spring_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ApiResponse<Object> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
