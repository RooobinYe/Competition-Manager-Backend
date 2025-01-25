package com.demo.spring_demo.service;

import com.demo.spring_demo.model.Member;
import com.demo.spring_demo.model.Team;
import com.demo.spring_demo.model.dto.TeamDTO;
import com.demo.spring_demo.model.ApiResponse;

public interface CaptainService {
    /**
     * 添加团队成员
     * @param teamId 团队ID
     * @param member 成员信息
     * @return 是否添加成功
     */
    boolean addTeamMember(Integer teamId, Member member);

    /**
     * 更新团队信息
     * @param team 团队信息
     * @return 更新后的团队信息
     */
    ApiResponse<TeamDTO> updateTeamInfo(Team team);

    /**
     * 移除团队成员
     * @param teamId 团队ID
     * @param memberId 成员ID
     * @return 是否移除成功
     */
    boolean removeTeamMember(Integer teamId, Integer memberId);

    /**
     * 更新团队成员信息
     * @param teamId 团队ID
     * @param member 成员信息
     * @return 是否更新成功
     */
    boolean updateTeamMember(Integer teamId, Member member);
} 