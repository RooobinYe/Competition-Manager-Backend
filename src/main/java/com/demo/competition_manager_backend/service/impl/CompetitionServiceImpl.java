package com.demo.competition_manager_backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "competitionCache", key = "'all'", unless = "#result.errCode != 0")
    public ApiResponse<List<Competition>> getAllCompetitions() {
        // 直接从数据库查询，Spring会自动处理缓存
        List<Competition> competitions = competitionMapper.selectList(null);
        return ApiResponse.success(competitions);
    }

    @Override
    @Cacheable(value = "competitionCache", key = "'user:' + #userCode", unless = "#result.errCode != 0")
    public ApiResponse<List<Competition>> getCompetitionsByUserId(String userCode) {
        // 直接从数据库查询，Spring会自动处理缓存
        List<Competition> competitions = competitionMapper.selectUserCompetitions(userCode);
        return ApiResponse.success(competitions);
    }
} 