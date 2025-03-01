package com.demo.competition_manager_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.competition_manager_backend.annotation.RequireRole;
import com.demo.competition_manager_backend.model.ApiResponse;
import com.demo.competition_manager_backend.model.dto.admin.competitionDTO;
import com.demo.competition_manager_backend.service.AdminService;

@RequireRole({3})
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/competition")
    public ApiResponse<Object> addCompetition(@RequestBody competitionDTO competitionDTO) {
        return adminService.addCompetition(competitionDTO);
    }
}
