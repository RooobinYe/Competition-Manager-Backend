package com.demo.competition_manager_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.competition_manager_backend.mapper.UserMapper;
import com.demo.competition_manager_backend.model.ApiResponse;
import com.demo.competition_manager_backend.model.User;
import com.demo.competition_manager_backend.service.UserService;
import com.demo.competition_manager_backend.model.dto.LoginRequest;
import com.demo.competition_manager_backend.model.dto.LoginResponse;
import com.demo.competition_manager_backend.utils.JwtUtils;
import com.demo.competition_manager_backend.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    @CacheEvict(value = "userCache", allEntries = true) // 添加用户时清除所有用户缓存
    public ApiResponse<Object> addUser(User user) {
        // 对密码进行加密存储
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int result = userMapper.insert(user);
        if (result == 1) {
            return ApiResponse.success(Collections.emptyMap());
        }
        return ApiResponse.error(500, "Failed to add user");
    }

    @Override
    @Cacheable(value = "userCache", key = "#loginRequest.userCode", unless = "#result.errCode != 0") // 只有在成功登录时才缓存
    public ApiResponse<LoginResponse> login(LoginRequest loginRequest) {
        // 1. 查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserCode, loginRequest.getUserCode());
        User user = userMapper.selectOne(queryWrapper);
        
        if (user == null) {
            return ApiResponse.error(401, "用户不存在");
        }

        // 2. 验证密码 - 使用安全的密码比较
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
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
