package com.demo.competition_manager_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.competition_manager_backend.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
