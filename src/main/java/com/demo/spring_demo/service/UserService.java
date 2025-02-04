package com.demo.spring_demo.service;

import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.model.User;

public interface UserService {

    /**
     * 添加用户
     * @param user 用户信息
     * @return 是否添加成功
     */
    ApiResponse<Object> addUser(User user);
}
