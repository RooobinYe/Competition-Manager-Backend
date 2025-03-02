package com.demo.competition_manager_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.competition_manager_backend.annotation.RequireRole;
import com.demo.competition_manager_backend.model.ApiResponse;
import com.demo.competition_manager_backend.model.Team;
import com.demo.competition_manager_backend.service.AcademyService;

@RequireRole({2,3})
@RestController
@RequestMapping("/academy")
public class AcademyController {

    @Autowired
    private AcademyService academyService;

    @GetMapping("/team")
    public ApiResponse<List<Team>> getTeam(@RequestParam(required = false) String academyId) {
        return academyService.getTeam(academyId);
    }
}
