package com.demo.spring_demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.spring_demo.model.Team;

@Mapper
public interface AcademyMapper extends BaseMapper<Team> {
} 