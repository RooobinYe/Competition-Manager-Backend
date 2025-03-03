package com.sast.approval.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sast.approval.model.Team;

@Mapper
public interface AcademyMapper extends BaseMapper<Team> {
} 