package com.demo.competition_manager_backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.competition_manager_backend.mapper.AcademyMapper;
import com.demo.competition_manager_backend.model.Team;
import com.demo.competition_manager_backend.service.AcademyService;

@Service
public class AcademyServiceImpl implements AcademyService {
    
    @Autowired
    private AcademyMapper academyMapper;

    @Override
    public List<Team> getTeam(String academyId) {
        return academyMapper.selectList(null);
    }
}
