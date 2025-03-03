package com.sast.approval.service;

import com.sast.approval.model.ApiResponse;
import com.sast.approval.model.User;
import com.sast.approval.model.dto.LoginRequest;
import com.sast.approval.model.dto.LoginResponse;

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
