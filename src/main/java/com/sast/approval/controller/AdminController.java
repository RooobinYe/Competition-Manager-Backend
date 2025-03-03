package com.sast.approval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sast.approval.annotation.RequireRole;
import com.sast.approval.model.ApiResponse;
import com.sast.approval.model.dto.admin.competitionDTO;
import com.sast.approval.service.AdminService;

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
