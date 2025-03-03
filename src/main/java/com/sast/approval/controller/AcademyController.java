package com.sast.approval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sast.approval.annotation.RequireRole;
import com.sast.approval.model.ApiResponse;
import com.sast.approval.model.Team;
import com.sast.approval.service.AcademyService;

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
