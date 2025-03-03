package com.sast.approval.service;

import com.sast.approval.model.ApiResponse;
import com.sast.approval.model.dto.admin.competitionDTO;

public interface AdminService {
    /**
     * 添加比赛
     * @param competitionDTO 比赛信息
     * @return 比赛信息
     */
    ApiResponse<Object> addCompetition(competitionDTO competitionDTO);
}
