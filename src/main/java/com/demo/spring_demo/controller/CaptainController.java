package com.demo.spring_demo.controller;

import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.model.Member;
import com.demo.spring_demo.model.dto.TeamDTO;
import com.demo.spring_demo.model.dto.MemberResponseDTO;
import com.demo.spring_demo.service.CaptainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.demo.spring_demo.mapper.TeamMapper;
import com.demo.spring_demo.model.Team;
import java.util.List;

@RestController
@RequestMapping("/captain")
public class CaptainController {

    @Autowired
    private CaptainService captainService;

    @Autowired
    private TeamMapper teamMapper;

    @PostMapping("/team")
    public ApiResponse<TeamDTO> updateTeamInfo(@RequestBody Team team) {
        return captainService.updateTeamInfo(team);
    }

    @GetMapping("/team/{teamId}/member")
    public ApiResponse<List<MemberResponseDTO>> getTeamMembers(@PathVariable Integer teamId) {
        return captainService.getTeamMembers(teamId);
    }
}
