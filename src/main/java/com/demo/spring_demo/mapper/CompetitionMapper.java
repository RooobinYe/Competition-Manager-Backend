package com.demo.spring_demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.spring_demo.model.Competition;

@Mapper
public interface CompetitionMapper extends BaseMapper<Competition> {
    
    /**
     * 查询用户参加的所有比赛
     * @param userCode 用户编号
     * @return 比赛列表
     */
    @Select("SELECT c.* FROM competition c " +
            "INNER JOIN user u ON c.id = u.com_id " +
            "WHERE u.user_code = #{userCode}")
    List<Competition> selectUserCompetitions(String userCode);
}
