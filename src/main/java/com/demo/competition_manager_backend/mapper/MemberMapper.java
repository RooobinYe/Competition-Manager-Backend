package com.demo.competition_manager_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.demo.competition_manager_backend.model.Member;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
} 