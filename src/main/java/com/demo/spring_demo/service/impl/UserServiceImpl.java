package com.demo.spring_demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.spring_demo.mapper.UserMapper;
import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.model.User;
import com.demo.spring_demo.service.UserService;
import com.demo.spring_demo.model.dto.LoginRequest;
import com.demo.spring_demo.model.dto.LoginResponse;
import com.demo.spring_demo.utils.JwtUtils;
import com.demo.spring_demo.utils.PasswordEncoder;
import com.demo.spring_demo.utils.RedisUtils;
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
    
    @Autowired
    private RedisUtils redisUtils;

    private static final String USER_CACHE_KEY = "user:";

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
        // 先尝试从Redis缓存获取用户信息
        String userCacheKey = USER_CACHE_KEY + loginRequest.getUserCode();
        User cachedUser = redisUtils.get(userCacheKey, User.class);
        
        if (cachedUser == null) {
            // 1. 查询用户
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUserCode, loginRequest.getUserCode());
            User user = userMapper.selectOne(queryWrapper);
            
            if (user == null) {
                return ApiResponse.error(401, "用户不存在");
            }
            
            // 将用户信息存入Redis缓存，有效期30分钟
            redisUtils.set(userCacheKey, user, 1800);
            cachedUser = user;
        }

        // 2. 验证密码 - 使用安全的密码比较
        if (!passwordEncoder.matches(loginRequest.getPassword(), cachedUser.getPassword())) {
            return ApiResponse.error(401, "密码错误");
        }

        // 3. 生成token
        String token = jwtUtils.generateToken(cachedUser.getUserCode(), String.valueOf(cachedUser.getRole()));

        // 4. 构建响应
        LoginResponse response = new LoginResponse();
        response.setToken(token);

        return ApiResponse.success(response);
    }
}
