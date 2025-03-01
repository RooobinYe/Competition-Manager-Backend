package com.demo.competition_manager_backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.competition_manager_backend.mapper.CompetitionMapper;
import com.demo.competition_manager_backend.model.Competition;
import com.demo.competition_manager_backend.service.CompetitionService;
import com.demo.competition_manager_backend.model.ApiResponse;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionMapper competitionMapper;

    @Override
    public ApiResponse<List<Competition>> getAllCompetitions() {
        List<Competition> competitions = competitionMapper.selectList(null);
        return ApiResponse.success(competitions);
    }

    @Override
    public ApiResponse<List<Competition>> getCompetitionsByUserId(String userCode) {
        List<Competition> competitions = competitionMapper.selectUserCompetitions(userCode);
        return ApiResponse.success(competitions);
    }
} 