package com.sast.approval.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.sast.approval.model.Member;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
} 