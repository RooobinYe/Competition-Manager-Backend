package com.sast.approval.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sast.approval.mapper.TeamMapper;
import com.sast.approval.mapper.MemberMapper;
import com.sast.approval.mapper.InstructorMapper;
import com.sast.approval.model.Team;
import com.sast.approval.model.Member;
import com.sast.approval.model.Instructor;
import com.sast.approval.model.dto.captain.TeamDTO;
import com.sast.approval.model.ApiResponse;
import com.sast.approval.model.dto.captain.GetMemberDTO;
import com.sast.approval.service.CaptainService;
import com.sast.approval.exception.BusinessException;
import com.sast.approval.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaptainServiceImpl implements CaptainService {

    @Autowired
    private TeamMapper teamMapper;
    
    @Autowired
    private MemberMapper memberMapper;
    
    @Autowired
    private InstructorMapper instructorMapper;

    @Override
    @Transactional
    @CacheEvict(value = {"captainCache", "teamCache"}, allEntries = true)
    public ApiResponse<TeamDTO> updateTeamInfo(Team team) {
        Team existingTeam = teamMapper.selectById(team.getId());
        boolean isNewTeam = existingTeam == null;
        
        boolean result;
        if (isNewTeam) {
            result = teamMapper.insert(team) > 0;
        } else {
            result = teamMapper.updateById(team) > 0;
        }
        
        if (!result) {
            throw new BusinessException(
                isNewTeam ? ErrorCode.TEAM_CREATE_FAILED : ErrorCode.TEAM_UPDATE_FAILED
            );
        }

        if (team.getMemberIds() != null && !team.getMemberIds().isEmpty()) {
            LambdaQueryWrapper<Member> memberWrapper = new LambdaQueryWrapper<>();
            memberWrapper.eq(Member::getTeamId, team.getId());
            memberMapper.delete(memberWrapper);
            
            for (Integer memberId : team.getMemberIds()) {
                Member existingMember = memberMapper.selectById(memberId);
                Member member;
                
                if (existingMember != null) {
                    member = existingMember;
                } else {
                    member = new Member();
                    member.setId(memberId);
                    member.setName("待更新");
                    member.setStudentId("TEMP_" + team.getId() + "_" + memberId);
                    member.setAcademyId(0);
                    member.setPhone("未设置");
                    member.setEmail("未设置");
                }
                
                member.setTeamId(team.getId());
                member.setIsCaptain(memberId.equals(team.getCaptainId()) ? 1 : 0);
                memberMapper.insert(member);
            }
        }

        if (team.getInstructorIds() != null && !team.getInstructorIds().isEmpty()) {
            LambdaQueryWrapper<Instructor> instructorWrapper = new LambdaQueryWrapper<>();
            instructorWrapper.eq(Instructor::getTeamId, team.getId());
            instructorMapper.delete(instructorWrapper);
            
            for (Integer instructorId : team.getInstructorIds()) {
                Instructor existingInstructor = instructorMapper.selectById(instructorId.longValue());
                Instructor instructor;
                
                if (existingInstructor != null) {
                    instructor = existingInstructor;
                } else {
                    instructor = new Instructor();
                    instructor.setId(instructorId.longValue());
                    instructor.setName("待更新");
                    instructor.setWorkCode("TEMP_" + team.getId() + "_" + instructorId);
                    instructor.setAcademyId(0L);
                    instructor.setPhone("未设置");
                }
                
                instructor.setTeamId(team.getId().longValue());
                instructorMapper.insert(instructor);
            }
        }

        LambdaQueryWrapper<Member> memberWrapper = new LambdaQueryWrapper<>();
        memberWrapper.eq(Member::getTeamId, team.getId());
        List<Member> members = memberMapper.selectList(memberWrapper);
        String memberNames = members.stream()
            .map(Member::getName)
            .collect(Collectors.joining(", "));
        
        LambdaQueryWrapper<Instructor> instructorWrapper = new LambdaQueryWrapper<>();
        instructorWrapper.eq(Instructor::getTeamId, team.getId().longValue());
        List<Instructor> instructors = instructorMapper.selectList(instructorWrapper);
        String instructorNames = instructors.stream()
            .map(Instructor::getName)
            .collect(Collectors.joining(", "));
        
        LambdaUpdateWrapper<Team> teamWrapper = new LambdaUpdateWrapper<>();
        teamWrapper.eq(Team::getId, team.getId())
                    .set(Team::getMemberNames, memberNames)
                    .set(Team::getInstructorNames, instructorNames);
        teamMapper.update(null, teamWrapper);

        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setComId(team.getComId());
        teamDTO.setName(team.getName());
        teamDTO.setCaptainId(team.getCaptainId());
        teamDTO.setCaptainName(team.getCaptainName());
        teamDTO.setStatus(team.getStatus());
        teamDTO.setMemberNames(memberNames);
        teamDTO.setInstructorNames(instructorNames);

        return ApiResponse.success(teamDTO);
    }

    @Override
    @Transactional
    @Cacheable(value = "captainCache", key = "'team_members:' + #teamId")
    public ApiResponse<List<GetMemberDTO>> getTeamMembers(Integer teamId) {
        Team team = teamMapper.selectById(teamId);
        if (team == null) {
            throw BusinessException.teamNotFound(teamId);
        }

        LambdaQueryWrapper<Member> memberWrapper = new LambdaQueryWrapper<>();
        memberWrapper.eq(Member::getTeamId, teamId);
        List<Member> members = memberMapper.selectList(memberWrapper);
        
        if (members.isEmpty()) {
            return ApiResponse.error(404, "No members found for team");
        }
        
        List<GetMemberDTO> memberDTOs = members.stream()
            .map(member -> {
                GetMemberDTO dto = new GetMemberDTO();
                dto.setStudentId(member.getStudentId());
                dto.setName(member.getName());
                dto.setAcademy("Unknown");
                return dto;
            })
            .collect(Collectors.toList());
        
        return ApiResponse.success(memberDTOs);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"captainCache", "teamCache"}, key = "'team_members:' + #teamId")
    public ApiResponse<Object> addTeamMember(Integer teamId, Member member) {
        Team team = teamMapper.selectById(teamId);
        if (team == null) {
            throw BusinessException.teamNotFound(teamId);
        }

        member.setTeamId(teamId);
        boolean result = memberMapper.insert(member) > 0;

        if (!result) {
            throw new BusinessException(ErrorCode.MEMBER_CREATE_FAILED);
        }

        LambdaQueryWrapper<Member> memberWrapper = new LambdaQueryWrapper<>();
        memberWrapper.eq(Member::getTeamId, teamId);
        List<Member> members = memberMapper.selectList(memberWrapper);
        String memberNames = members.stream()
                .map(Member::getName)
                .collect(Collectors.joining(", "));

        LambdaUpdateWrapper<Team> teamWrapper = new LambdaUpdateWrapper<>();
        teamWrapper.eq(Team::getId, teamId)
                .set(Team::getMemberNames, memberNames);
        teamMapper.update(null, teamWrapper);

        return ApiResponse.success(Collections.emptyMap());
    }
    
    @Override
    @Transactional
    @Cacheable(value = "teamCache", key = "'team_list'")
    public ApiResponse<List<TeamDTO>> getTeamList() {
        List<Team> teams = teamMapper.selectList(null);

        List<TeamDTO> teamDTOs = teams.stream()
                .map(team -> {
                    TeamDTO teamDTO = new TeamDTO();
                    teamDTO.setId(team.getId());
                    teamDTO.setComId(team.getComId());
                    teamDTO.setName(team.getName());
                    teamDTO.setCaptainId(team.getCaptainId());
                    teamDTO.setCaptainName(team.getCaptainName());
                    teamDTO.setStatus(team.getStatus());
                    teamDTO.setMemberNames(team.getMemberNames());
                    teamDTO.setInstructorNames(team.getInstructorNames());
                    return teamDTO;
                })
                .collect(Collectors.toList());
        
        return ApiResponse.success(teamDTOs);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"captainCache", "teamCache"}, key = "'team_members:' + #teamId")
    public ApiResponse<Object> deleteTeamMember(Integer teamId, Integer memberId) {
        LambdaQueryWrapper<Member> memberWrapper = new LambdaQueryWrapper<>();
        memberWrapper.eq(Member::getTeamId, teamId)
                .eq(Member::getId, memberId);
        memberMapper.delete(memberWrapper);

        memberWrapper = new LambdaQueryWrapper<>();
        memberWrapper.eq(Member::getTeamId, teamId);
        List<Member> members = memberMapper.selectList(memberWrapper);
        String memberNames = members.stream()
                .map(Member::getName)
                .collect(Collectors.joining(", "));

        LambdaUpdateWrapper<Team> teamWrapper = new LambdaUpdateWrapper<>();
        teamWrapper.eq(Team::getId, teamId)
                .set(Team::getMemberNames, memberNames);
        teamMapper.update(null, teamWrapper);

        return ApiResponse.success(Collections.emptyMap());
    }

    @Override
    @Transactional
    @CacheEvict(value = {"captainCache", "teamCache"}, key = "'team_members:' + #teamId")
    public ApiResponse<Object> updateTeamMember(Integer teamId, Member member) {
        LambdaQueryWrapper<Member> memberWrapper = new LambdaQueryWrapper<>();
        memberWrapper.eq(Member::getTeamId, teamId)
                .eq(Member::getId, member.getId());
        memberMapper.update(member, memberWrapper);

        memberWrapper = new LambdaQueryWrapper<>();
        memberWrapper.eq(Member::getTeamId, teamId);
        String memberNames = memberMapper.selectList(memberWrapper).stream()
                .map(Member::getName)
                .collect(Collectors.joining(", "));

        LambdaUpdateWrapper<Team> teamWrapper = new LambdaUpdateWrapper<>();
        teamWrapper.eq(Team::getId, teamId)
                .set(Team::getMemberNames, memberNames);
        teamMapper.update(null, teamWrapper);

        return ApiResponse.success(Collections.emptyMap());
    }
} 

