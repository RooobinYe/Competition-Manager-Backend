package com.demo.spring_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.demo.spring_demo.model.Instructor;

@Mapper
public interface InstructorMapper extends BaseMapper<Instructor> {
} 