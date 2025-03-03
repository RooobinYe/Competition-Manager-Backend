package com.sast.approval.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sast.approval.mapper.AcademyMapper;
import com.sast.approval.model.ApiResponse;
import com.sast.approval.model.Team;
import com.sast.approval.service.AcademyService;

@Service
public class AcademyServiceImpl implements AcademyService {
    
    @Autowired
    private AcademyMapper academyMapper;

    @Override
    @Cacheable(value = "academyCache", key = "#academyId != null ? #academyId : 'all'")
    public ApiResponse<List<Team>> getTeam(String academyId) {
        // 直接从数据库查询并返回结果，Spring会自动处理缓存
        List<Team> teams = academyMapper.selectList(null);
        return ApiResponse.success(teams);
    }
}
