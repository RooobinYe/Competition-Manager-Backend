package com.demo.spring_demo.controller;

import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.model.dto.captain.UpdateTeamDTO;
import com.demo.spring_demo.model.dto.captain.GetMemberDTO;
import com.demo.spring_demo.model.dto.captain.AddMemberDTO;
import com.demo.spring_demo.service.CaptainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.demo.spring_demo.model.Team;
import com.demo.spring_demo.model.Member;
import java.util.List;

@RestController
@RequestMapping("/captain")
public class CaptainController {

    @Autowired
    private CaptainService captainService;

    @PostMapping("/team")
    public ApiResponse<UpdateTeamDTO> updateTeamInfo(@RequestBody Team team) {
        return captainService.updateTeamInfo(team);
    }

    @GetMapping("/team/{teamId}/member")
    public ApiResponse<List<GetMemberDTO>> getTeamMembers(@PathVariable Integer teamId) {
        return captainService.getTeamMembers(teamId);
    }

    @PostMapping("/team/{teamId}/member")
    public ApiResponse<Void> addTeamMember(@PathVariable Integer teamId, @RequestBody Member member) {
        return captainService.addTeamMember(teamId, member);
    }

}
