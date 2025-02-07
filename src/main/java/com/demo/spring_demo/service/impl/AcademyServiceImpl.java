package com.demo.spring_demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring_demo.mapper.AcademyMapper;
import com.demo.spring_demo.model.Team;
import com.demo.spring_demo.service.AcademyService;

@Service
public class AcademyServiceImpl implements AcademyService {
    
    @Autowired
    private AcademyMapper academyMapper;

    @Override
    public List<Team> getTeam(String academyId) {
        return academyMapper.findByAcademyId(academyId);
    }
}
