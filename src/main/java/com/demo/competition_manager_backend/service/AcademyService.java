package com.demo.competition_manager_backend.service;

import java.util.List;

import com.demo.competition_manager_backend.model.ApiResponse;
import com.demo.competition_manager_backend.model.Team;

public interface AcademyService {
    ApiResponse<List<Team>> getTeam(String academyId);
}
