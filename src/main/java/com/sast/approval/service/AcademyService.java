package com.sast.approval.service;

import java.util.List;

import com.sast.approval.model.ApiResponse;
import com.sast.approval.model.Team;

public interface AcademyService {
    ApiResponse<List<Team>> getTeam(String academyId);
}
