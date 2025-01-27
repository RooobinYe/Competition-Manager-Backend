package com.demo.spring_demo.service.impl;

import com.demo.spring_demo.mapper.TeamMapper;
import com.demo.spring_demo.model.Team;
import com.demo.spring_demo.model.Member;
import com.demo.spring_demo.model.Instructor;
import com.demo.spring_demo.model.dto.captain.UpdateTeamDTO;
import com.demo.spring_demo.model.ApiResponse;
import com.demo.spring_demo.model.dto.captain.GetMemberDTO;
import com.demo.spring_demo.model.dto.captain.AddMemberDTO;
import com.demo.spring_demo.service.CaptainService;
import com.demo.spring_demo.exception.BusinessException;
import com.demo.spring_demo.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class CaptainServiceImpl implements CaptainService {

    @Autowired
    private TeamMapper teamMapper;

    @Override
    @Transactional
    public ApiResponse<UpdateTeamDTO> updateTeamInfo(Team team) {
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
            throw new BusinessException(
                isNewTeam ? ErrorCode.TEAM_CREATE_FAILED : ErrorCode.TEAM_UPDATE_FAILED
            );
        }

        // 处理成员关系
        if (team.getMemberIds() != null && !team.getMemberIds().isEmpty()) {
            // 删除原有成员关系
            teamMapper.deleteTeamMembers(team.getId());
            
            // 添加新的成员关系
            for (Integer memberId : team.getMemberIds()) {
                Member existingMember = teamMapper.findMemberById(memberId);
                Member member;
                
                if (existingMember != null) {
                    // 如果成员存在，复用现有信息
                    member = existingMember;
                } else {
                    // 如果成员不存在，创建新成员并设置初始值
                    member = new Member();
                    member.setId(memberId);
                    member.setName("待更新"); // 设置初始名字
                    member.setStudentId("TEMP_" + team.getId() + "_" + memberId); // 使用团队ID和成员ID组合的临时学号
                    member.setAcademyId(0); // 设置初始学院ID
                    member.setPhone("未设置"); // 设置初始电话
                    member.setEmail("未设置"); // 设置初始邮箱
                }
                
                // 设置或更新团队相关信息
                member.setTeamId(team.getId());
                member.setIsCaptain(memberId.equals(team.getCaptainId()) ? 1 : 0);
                teamMapper.insertMember(member);
            }
        }

        // 处理导师关系
        if (team.getInstructorIds() != null && !team.getInstructorIds().isEmpty()) {
            // 删除原有导师关系
            teamMapper.deleteTeamInstructors(team.getId());
            
            // 添加新的导师关系
            for (Integer instructorId : team.getInstructorIds()) {
                Instructor existingInstructor = teamMapper.findInstructorById(instructorId.longValue());
                Instructor instructor;
                
                if (existingInstructor != null) {
                    // 如果导师存在，复用现有信息
                    instructor = existingInstructor;
                } else {
                    // 如果导师不存在，创建新导师并设置初始值
                    instructor = new Instructor();
                    instructor.setId(instructorId.longValue());
                    instructor.setName("待更新"); // 设置初始名字
                    instructor.setWorkCode("TEMP_" + team.getId() + "_" + instructorId); // 使用团队ID和导师ID组合的临时工号
                    instructor.setAcademyID(0L); // 设置初始学院ID
                    instructor.setPhone("未设置"); // 设置初始电话
                }
                
                // 设置或更新团队相关信息
                instructor.setTeamID(team.getId().longValue());
                teamMapper.insertInstructor(instructor);
            }
        }

        // 获取最新的成员和导师名字列表
        List<Member> members = teamMapper.getTeamMembers(team.getId());
        String memberNames = members.stream()
            .map(Member::getName)
            .collect(Collectors.joining(", "));
        
        // 更新团队中的成员名字列表
        teamMapper.updateTeamMemberNames(team.getId(), memberNames);

        // 构建返回数据
        UpdateTeamDTO updateTeamDTO = new UpdateTeamDTO();
        updateTeamDTO.setId(team.getId());
        updateTeamDTO.setComId(team.getComId());
        updateTeamDTO.setName(team.getName());
        updateTeamDTO.setCaptainId(team.getCaptainId());
        updateTeamDTO.setCaptainName(team.getCaptainName());
        updateTeamDTO.setStatus(team.getStatus());
        updateTeamDTO.setMemberNames(memberNames);
        updateTeamDTO.setInstructorNames(team.getInstructorNames());

        return ApiResponse.success(updateTeamDTO);
    }

    @Override
    @Transactional
    public ApiResponse<List<GetMemberDTO>> getTeamMembers(Integer teamId) {
        // 检查团队是否存在
        Team team = teamMapper.findById(teamId);
        if (team == null) {
            throw BusinessException.teamNotFound(teamId);
        }

        // 直接从member表获取成员列表
        List<Member> members = teamMapper.getTeamMembers(teamId);
        if (members.isEmpty()) {
            return ApiResponse.error(404, "No members found for team");
        }
        
        // 转换为DTO
        List<GetMemberDTO> memberDTOs = members.stream()
            .map(member -> {
                GetMemberDTO dto = new GetMemberDTO();
                dto.setStudentId(member.getStudentId());
                dto.setName(member.getName());
                // TODO: 需要从Academy服务获取学院名称
                dto.setAcademy("Unknown");
                return dto;
            })
            .collect(Collectors.toList());
        
        return ApiResponse.success(memberDTOs);
    }

    @Override
    @Transactional
    public ApiResponse<Void> addTeamMember(Integer teamId, Member member) {
        // 检查团队是否存在
        Team team = teamMapper.findById(teamId);
        if (team == null) {
            throw BusinessException.teamNotFound(teamId);
        }

        // 设置团队ID
        member.setTeamId(teamId);
        
        // 插入成员
        int result = teamMapper.insertMember(member);
        if (result <= 0) {
            throw new BusinessException(ErrorCode.MEMBER_CREATE_FAILED);
        }

        // 更新团队的成员名字列表
        List<Member> members = teamMapper.getTeamMembers(teamId);
        String memberNames = members.stream()
            .map(Member::getName)
            .collect(Collectors.joining(", "));
        teamMapper.updateTeamMemberNames(teamId, memberNames);

        return ApiResponse.success(null);
    }
} 