package com.demo.competition_manager_backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.competition_manager_backend.model.Team;

@Mapper
public interface AcademyMapper extends BaseMapper<Team> {
} 