package com.demo.competition_manager_backend.service;

import java.util.List;
import com.demo.competition_manager_backend.model.Competition;
import com.demo.competition_manager_backend.model.ApiResponse;

public interface CompetitionService {
    /**
     * 获取所有比赛
     */
    ApiResponse<List<Competition>> getAllCompetitions();

    /**
     * 获取用户参加的比赛
     * @param userCode 用户编号
     */
    ApiResponse<List<Competition>> getCompetitionsByUserId(String userCode);
} 