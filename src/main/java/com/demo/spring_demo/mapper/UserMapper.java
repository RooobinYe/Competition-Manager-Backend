package com.demo.spring_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.spring_demo.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户代码查询用户
     * @param userCode 用户代码
     * @return 用户信息
     */
    User findByUserCode(String userCode);
}
