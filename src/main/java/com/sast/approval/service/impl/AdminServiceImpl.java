package com.sast.approval.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sast.approval.mapper.CompetitionMapper;
import com.sast.approval.model.ApiResponse;
import com.sast.approval.model.Competition;
import com.sast.approval.model.dto.admin.competitionDTO;
import com.sast.approval.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private CompetitionMapper competitionMapper;

    @Override
    public ApiResponse<Object> addCompetition(competitionDTO competitionDTO) {
        Competition competition = new Competition();
        competition.setName(competitionDTO.getName());
        competition.setDescription(competitionDTO.getDescription());
        competition.setMinTeamMembers(competitionDTO.getMinTeamMembers());
        competition.setMaxTeamMembers(competitionDTO.getMaxTeamMembers());
        // competition.setWorkCode(competitionDTO.getWorkCode());
        competition.setWorkCode("TEMP_NUMBERS");
        competition.setStartTime(competitionDTO.getStartTime());
        competition.setEndTime(competitionDTO.getEndTime());
        competition.setReviewBeginTime(competitionDTO.getReviewBeginTime());
        competition.setReviewEndTime(competitionDTO.getReviewEndTime());
        competition.setCreateTime(LocalDateTime.now());

        competitionMapper.insert(competition);
        return ApiResponse.success(competition);
    }
}
