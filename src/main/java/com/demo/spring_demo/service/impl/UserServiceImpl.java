package com.demo.spring_demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.spring_demo.mapper.UserMapper;
import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.model.User;
import com.demo.spring_demo.service.UserService;
import com.demo.spring_demo.model.dto.LoginRequest;
import com.demo.spring_demo.model.dto.LoginResponse;
import com.demo.spring_demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    @Transactional
    public ApiResponse<Object> addUser(User user) {
        int result = userMapper.insert(user);
        if (result == 1) {
            return ApiResponse.success(Collections.emptyMap());
        }
        return ApiResponse.error(500, "Failed to add user");
    }

    @Override
    public ApiResponse<LoginResponse> login(LoginRequest loginRequest) {
        // 1. 查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserCode, loginRequest.getUserCode());
        User user = userMapper.selectOne(queryWrapper);
        
        if (user == null) {
            return ApiResponse.error(401, "用户不存在");
        }

        // 2. 验证密码
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ApiResponse.error(401, "密码错误");
        }

        // 3. 生成token
        String token = jwtUtils.generateToken(user.getUserCode(), String.valueOf(user.getRole()));

        // 4. 构建响应
        LoginResponse response = new LoginResponse();
        response.setToken(token);

        return ApiResponse.success(response);
    }
}
