package com.demo.spring_demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.demo.spring_demo.model.Team;
import com.demo.spring_demo.model.Member;
import com.demo.spring_demo.model.Instructor;
import java.util.List;

@Mapper
public interface TeamMapper {
    // 查询团队
    Team findById(Integer id);
    List<Team> findAll();

    // 团队基本操作
    int insert(Team team);
    int updateTeam(Team team);
    int deleteById(Integer id);

    // 成员相关操作
    List<Member> getTeamMembers(@Param("teamId") Integer teamId);
    int updateTeamMemberNames(@Param("teamId") Integer teamId, @Param("memberNames") String memberNames);
    int insertMember(Member member);
    int deleteTeamMembers(@Param("teamId") Integer teamId);
    Member findMemberById(@Param("id") Integer id);
    
    // 导师相关操作
    List<Instructor> getTeamInstructors(@Param("teamId") Integer teamId);
    int updateTeamInstructorNames(@Param("teamId") Integer teamId, @Param("instructorNames") String instructorNames);
    int insertInstructor(Instructor instructor);
    int deleteTeamInstructors(@Param("teamId") Integer teamId);
    Instructor findInstructorById(@Param("id") Long id);
}