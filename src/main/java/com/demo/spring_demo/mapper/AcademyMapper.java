package com.demo.spring_demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.demo.spring_demo.model.Team;

@Mapper
public interface AcademyMapper {
    List<Team> findByAcademyId(@Param("academyId") String academyId);
} 