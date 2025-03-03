package com.sast.approval.controller;

import com.sast.approval.annotation.RequireRole;
import com.sast.approval.model.ApiResponse;
import com.sast.approval.model.dto.captain.TeamDTO;
import com.sast.approval.model.dto.captain.GetMemberDTO;
import com.sast.approval.model.dto.captain.DeleteMemberDTO;
import com.sast.approval.service.CaptainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sast.approval.model.Team;
import com.sast.approval.model.Member;
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
