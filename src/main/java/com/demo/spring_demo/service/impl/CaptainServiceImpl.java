package com.demo.spring_demo.service.impl;

import com.demo.spring_demo.mapper.MemberMapper;
import com.demo.spring_demo.mapper.TeamMapper;
import com.demo.spring_demo.model.Member;
import com.demo.spring_demo.model.Team;
import com.demo.spring_demo.model.dto.TeamDTO;
import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.service.CaptainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;

@Service
public class CaptainServiceImpl implements CaptainService {

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    @Transactional
    public boolean addTeamMember(Integer teamId, Member member) {
        // 检查团队是否存在
        Team team = teamMapper.findById(teamId);
        if (team == null) {
            throw new RuntimeException("Team not found");
        }

        // 检查邮箱是否已存在于该团队
        int count = memberMapper.checkEmailExistsInTeam(teamId, member.getEmail(), null);
        if (count > 0) {
            throw new RuntimeException("Email already exists in team");
        }

        // 设置团队ID
        member.setTeamId(teamId);

        // 插入成员
        return memberMapper.insert(member) > 0;
    }

    @Override
    @Transactional
    public ApiResponse<TeamDTO> updateTeamInfo(Team team) {
        try {
            Team existingTeam = teamMapper.findById(team.getId());
            boolean isNewTeam = existingTeam == null;
            
            // 创建或更新团队基本信息
            int result;
            if (isNewTeam) {
                result = teamMapper.insert(team);
            } else {
                result = teamMapper.updateTeam(team);
            }
            
            if (result <= 0) {
                return ApiResponse.error(500, isNewTeam ? "Failed to create team" : "Failed to update team info");
            }

            // 更新团队成员
            teamMapper.deleteTeamMembers(team.getId());
            if (team.getMemberIds() != null && !team.getMemberIds().isEmpty()) {
                teamMapper.insertTeamMembers(team.getId(), team.getMemberIds());
            }

            // 更新指导老师
            teamMapper.deleteTeamInstructors(team.getId());
            if (team.getInstructorIds() != null && !team.getInstructorIds().isEmpty()) {
                teamMapper.insertTeamInstructors(team.getId(), team.getInstructorIds());
            }

            // 构建返回数据
            TeamDTO teamDTO = new TeamDTO();
            teamDTO.setId(team.getId());
            teamDTO.setComId(team.getComId());
            teamDTO.setName(team.getName());
            teamDTO.setCaptainId(team.getCaptainId());
            teamDTO.setCaptainName(team.getCaptainName());
            teamDTO.setStatus(team.getStatus());
            teamDTO.setMemberNames(teamMapper.getTeamMemberNames(team.getId()));
            teamDTO.setInstructorNames(teamMapper.getTeamInstructorNames(team.getId()));

            return ApiResponse.success(teamDTO);
        } catch (Exception e) {
            return ApiResponse.error(500, e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean removeTeamMember(Integer teamId, Integer memberId) {
        // 检查团队是否存在
        Team team = teamMapper.findById(teamId);
        if (team == null) {
            throw new RuntimeException("Team not found");
        }

        // 检查成员是否存在
        Member member = memberMapper.findById(memberId);
        if (member == null || !teamId.equals(member.getTeamId())) {
            throw new RuntimeException("Member not found in team");
        }

        // 删除成员
        return memberMapper.deleteById(memberId) > 0;
    }

    @Override
    @Transactional
    public boolean updateTeamMember(Integer teamId, Member member) {
        // 检查团队是否存在
        Team team = teamMapper.findById(teamId);
        if (team == null) {
            throw new RuntimeException("Team not found");
        }

        // 检查成员是否存在
        Member existingMember = memberMapper.findById(member.getId());
        if (existingMember == null || !teamId.equals(existingMember.getTeamId())) {
            throw new RuntimeException("Member not found in team");
        }

        // 检查新邮箱是否与其他成员冲突
        int count = memberMapper.checkEmailExistsInTeam(teamId, member.getEmail(), member.getId());
        if (count > 0) {
            throw new RuntimeException("Email already exists in team");
        }

        // 设置团队ID
        member.setTeamId(teamId);

        // 更新成员信息
        return memberMapper.update(member) > 0;
    }
} 