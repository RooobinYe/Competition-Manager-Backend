package com.sast.approval.service;

import java.util.List;
import com.sast.approval.model.Competition;
import com.sast.approval.model.ApiResponse;

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