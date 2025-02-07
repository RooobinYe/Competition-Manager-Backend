package com.demo.spring_demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring_demo.mapper.CompetitionMapper;
import com.demo.spring_demo.model.Competition;
import com.demo.spring_demo.service.CompetitionService;
import com.demo.spring_demo.model.ApiResponse;

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