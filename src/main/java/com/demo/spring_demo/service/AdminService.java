package com.demo.spring_demo.service;

import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.model.dto.admin.competitionDTO;

public interface AdminService {
    /**
     * 添加比赛
     * @param competitionDTO 比赛信息
     * @return 比赛信息
     */
    ApiResponse<Object> addCompetition(competitionDTO competitionDTO);
}
