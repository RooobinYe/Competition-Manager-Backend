package com.demo.spring_demo.controller;

import com.demo.spring_demo.annotation.RequireRole;
import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.model.dto.captain.TeamDTO;
import com.demo.spring_demo.model.dto.captain.GetMemberDTO;
import com.demo.spring_demo.model.dto.captain.DeleteMemberDTO;
import com.demo.spring_demo.service.CaptainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.demo.spring_demo.model.Team;
import com.demo.spring_demo.model.Member;
import java.util.List;

@RestController
@RequestMapping("/captain")
@RequireRole({0, 1, 2, 3})
public class CaptainController {

    @Autowired
    private CaptainService captainService;

    @PostMapping("/team")
    public ApiResponse<TeamDTO> updateTeamInfo(@RequestBody Team team) {
        return captainService.updateTeamInfo(team);
    }

    @GetMapping("/team/{teamId}/member")
    public ApiResponse<List<GetMemberDTO>> getTeamMembers(@PathVariable Integer teamId) {
        return captainService.getTeamMembers(teamId);
    }

    @PostMapping("/team/{teamId}/member")
    public ApiResponse<Object> addTeamMember(@PathVariable Integer teamId, @RequestBody Member member) {
        return captainService.addTeamMember(teamId, member);
    }

    @GetMapping("/team")
    public ApiResponse<List<TeamDTO>> getTeamList() {
        return captainService.getTeamList();
    }

    @DeleteMapping("/team/{teamId}/member")
    public ApiResponse<Object> deleteTeamMember(@PathVariable Integer teamId, @RequestBody DeleteMemberDTO dto) {
        return captainService.deleteTeamMember(teamId, dto.getId());
    }

    @PatchMapping("/team/{teamId}/member")
    public ApiResponse<Object> updateTeamMember(@PathVariable Integer teamId, @RequestBody Member member) {
        return captainService.updateTeamMember(teamId, member);
    }
}
