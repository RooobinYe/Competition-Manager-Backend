package com.demo.spring_demo.service;

import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.model.User;
import com.demo.spring_demo.model.dto.LoginRequest;
import com.demo.spring_demo.model.dto.LoginResponse;

public interface UserService {

    /**
     * 添加用户
     * @param user 用户信息
     * @return 是否添加成功
     */
    ApiResponse<Object> addUser(User user);

    /**
     * 用户登录
     * @param loginRequest 登录请求
     * @return 登录响应
     */
    ApiResponse<LoginResponse> login(LoginRequest loginRequest);
}
