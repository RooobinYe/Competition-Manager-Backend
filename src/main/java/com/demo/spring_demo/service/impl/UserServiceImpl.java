package com.demo.spring_demo.service.impl;

import com.demo.spring_demo.mapper.UserMapper;
import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.model.User;
import com.demo.spring_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public ApiResponse<Object> addUser(User user) {
        int result = userMapper.insert(user);
        if (result == 1) {
            return ApiResponse.success(Collections.emptyMap());
        }
        return ApiResponse.error(500, "Failed to add user");
    }
}
