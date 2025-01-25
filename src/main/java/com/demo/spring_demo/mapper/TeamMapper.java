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
    
    /**
     * 获取团队成员ID列表
     * @param teamId 团队ID
     * @return 成员ID列表
     */
    List<Integer> getTeamMemberIds(@Param("teamId") Integer teamId);
    
    /**
     * 获取团队指导老师ID列表
     * @param teamId 团队ID
     * @return 指导老师ID列表
     */
    List<Integer> getTeamInstructorIds(@Param("teamId") Integer teamId);

    String getTeamMemberNames(@Param("teamId") Integer teamId);

    String getTeamInstructorNames(@Param("teamId") Integer teamId);

    /**
     * 插入单个团队成员关系
     * @param teamId 团队ID
     * @param memberId 成员ID
     * @return 影响的行数
     */
    int insertTeamMember(Integer teamId, Integer memberId);

    /**
     * 删除团队成员关系
     * @param teamId 团队ID
     * @param memberId 成员ID
     * @return 影响的行数
     */
    int deleteTeamMember(@Param("teamId") Integer teamId, @Param("memberId") Integer memberId);

    /**
     * 更新团队成员关系
     * @param teamId 团队ID
     * @param memberId 成员ID
     * @return 影响的行数
     */
    int updateTeamMember(@Param("teamId") Integer teamId, @Param("memberId") Integer memberId);
} 