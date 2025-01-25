package com.demo.spring_demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.demo.spring_demo.model.Member;
import java.util.List;

@Mapper
public interface MemberMapper {
    Member findById(Integer id);
    
    List<Member> findAll();
    
    List<Member> findByTeamId(@Param("teamId") Integer teamId);
    
    int insert(Member member);
    
    int update(Member member);
    
    int deleteById(Integer id);
    
    int deleteByTeamId(@Param("teamId") Integer teamId);
    
    int batchInsert(List<Member> members);
    
    int checkEmailExistsInTeam(@Param("teamId") Integer teamId, 
                              @Param("email") String email, 
                              @Param("id") Integer id);
} 