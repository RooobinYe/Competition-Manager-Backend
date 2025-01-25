package com.demo.spring_demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.demo.spring_demo.model.Team;
import java.util.List;

@Mapper
public interface TeamMapper {
    // 初始化表结构
    int createTeamTable();
    int createTeamMemberTable();
    int createTeamInstructorTable();
    
    Team findById(Integer id);
    
    List<Team> findAll();
    
    int insert(Team team);
    
    int updateTeam(Team team);
    
    int deleteById(Integer id);
    
    int deleteTeamMembers(@Param("teamId") Integer teamId);
    
    int deleteTeamInstructors(@Param("teamId") Integer teamId);
    
    int insertTeamMembers(@Param("teamId") Integer teamId, @Param("memberIds") List<Integer> memberIds);
    
    int insertTeamInstructors(@Param("teamId") Integer teamId, @Param("instructorIds") List<Integer> instructorIds);
    
    List<Integer> getTeamMemberIds(@Param("teamId") Integer teamId);
    
    List<Integer> getTeamInstructorIds(@Param("teamId") Integer teamId);

    String getTeamMemberNames(@Param("teamId") Integer teamId);

    String getTeamInstructorNames(@Param("teamId") Integer teamId);
} 