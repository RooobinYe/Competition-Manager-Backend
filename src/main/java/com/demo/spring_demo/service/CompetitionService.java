package com.demo.spring_demo.service;

import java.util.List;
import com.demo.spring_demo.model.Competition;
import com.demo.spring_demo.model.ApiResponse;

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