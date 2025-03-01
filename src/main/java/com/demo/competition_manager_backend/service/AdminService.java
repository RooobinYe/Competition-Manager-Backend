package com.demo.competition_manager_backend.service;

import com.demo.competition_manager_backend.model.ApiResponse;
import com.demo.competition_manager_backend.model.dto.admin.competitionDTO;

public interface AdminService {
    /**
     * 添加比赛
     * @param competitionDTO 比赛信息
     * @return 比赛信息
     */
    ApiResponse<Object> addCompetition(competitionDTO competitionDTO);
}
