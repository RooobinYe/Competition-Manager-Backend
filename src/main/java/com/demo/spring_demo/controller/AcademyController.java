package com.demo.spring_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring_demo.annotation.RequireRole;
import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.service.AcademyService;

@RequireRole({2,3})
@RestController
@RequestMapping("/academy")
public class AcademyController {

    @Autowired
    private AcademyService academyService;

    @GetMapping("/team")
    public ApiResponse<Object> getTeam(@RequestParam(required = false) String academyId) {
        return ApiResponse.success(academyService.getTeam(academyId));
    }
}
