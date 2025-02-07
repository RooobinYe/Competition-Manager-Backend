package com.demo.spring_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.model.Competition;
import com.demo.spring_demo.service.CompetitionService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/competitions")
public class CompetitionsController {
    
    @Autowired
    private CompetitionService competitionService;

    @GetMapping
    public ApiResponse<List<Competition>> getCompetitions(HttpServletRequest request) {
        String roleStr = (String) request.getAttribute("role");
        String userId = (String) request.getAttribute("userId");
        
        if (roleStr == null) {
            return ApiResponse.error(401, "未登录或登录已过期");
        }
        
        int role = Integer.parseInt(roleStr);
        if (role == 3 || role == 2) {
            return competitionService.getAllCompetitions();
        } else if (role == 0) {
            return competitionService.getCompetitionsByUserId(userId);
        } else {
            return ApiResponse.error(403, "无权限查看比赛列表");
        }
    }
}
