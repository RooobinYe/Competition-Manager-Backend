package com.demo.competition_manager_backend.service;

import com.demo.competition_manager_backend.model.ApiResponse;
import com.demo.competition_manager_backend.model.User;
import com.demo.competition_manager_backend.model.dto.LoginRequest;
import com.demo.competition_manager_backend.model.dto.LoginResponse;

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
