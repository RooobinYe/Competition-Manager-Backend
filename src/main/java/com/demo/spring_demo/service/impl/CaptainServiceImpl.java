package com.demo.spring_demo.service.impl;

import com.demo.spring_demo.mapper.TeamMapper;
import com.demo.spring_demo.model.Team;
import com.demo.spring_demo.model.dto.TeamDTO;
import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.model.dto.MemberResponseDTO;
import com.demo.spring_demo.service.CaptainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;

@Service
public class CaptainServiceImpl implements CaptainService {

    @Autowired
    private TeamMapper teamMapper;

    @Override
    @Transactional
    public boolean addTeamMember(Integer teamId, Integer memberId) {
        // 检查团队是否存在
        Team team = teamMapper.findById(teamId);
        if (team == null) {
            throw new RuntimeException("Team not found");
        }

        // 检查成员是否已在团队中
        List<Integer> memberIds = teamMapper.getTeamMemberIds(teamId);
        if (memberIds.contains(memberId)) {
            throw new RuntimeException("Member already exists in team");
        }

        // 插入团队成员关系
        return teamMapper.insertTeamMember(teamId, memberId) > 0;
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

        // 检查成员是否在团队中
        List<Integer> memberIds = teamMapper.getTeamMemberIds(teamId);
        if (!memberIds.contains(memberId)) {
            throw new RuntimeException("Member not found in team");
        }

        // 删除团队成员关系
        return teamMapper.deleteTeamMember(teamId, memberId) > 0;
    }

    @Override
    @Transactional
    public boolean updateTeamMember(Integer teamId, Integer memberId) {
        // 检查团队是否存在
        Team team = teamMapper.findById(teamId);
        if (team == null) {
            throw new RuntimeException("Team not found");
        }

        // 检查成员是否存在
        List<Integer> memberIds = teamMapper.getTeamMemberIds(teamId);
        if (!memberIds.contains(memberId)) {
            throw new RuntimeException("Member not found in team");
        }

        // 更新团队成员
        return teamMapper.updateTeamMember(teamId, memberId) > 0;
    }

    @Override
    @Transactional
    public ApiResponse<List<MemberResponseDTO>> getTeamMembers(Integer teamId) {
        try {
            List<Integer> memberIds = teamMapper.getTeamMemberIds(teamId);
            if (memberIds.isEmpty()) {
                return ApiResponse.error(404, "No members found for team");
            }
            
            List<MemberResponseDTO> memberResponseDTOs = new ArrayList<>();
            for (Integer memberId : memberIds) {
                MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
                memberResponseDTO.setStudentId(memberId.toString());
                // 由于我们只有 memberId，这里需要从其他地方获取 name 和 academy
                memberResponseDTO.setName("Member " + memberId); // 临时使用 ID 作为名字
                memberResponseDTO.setAcademy("Unknown");
                memberResponseDTOs.add(memberResponseDTO);
            }
            
            return ApiResponse.success(memberResponseDTOs);
        } catch (Exception e) {
            return ApiResponse.error(500, e.getMessage());
        }
    }
} 