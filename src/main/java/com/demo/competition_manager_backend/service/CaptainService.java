package com.demo.competition_manager_backend.service;

import com.demo.competition_manager_backend.model.Team;
import com.demo.competition_manager_backend.model.Member;
import com.demo.competition_manager_backend.model.dto.captain.TeamDTO;
import com.demo.competition_manager_backend.model.dto.captain.GetMemberDTO;
import com.demo.competition_manager_backend.model.ApiResponse;
import java.util.List;

public interface CaptainService {

    /**
     * 更新团队信息
     * @param team 团队信息
     * @return 更新后的团队信息
     */
    ApiResponse<TeamDTO> updateTeamInfo(Team team);

    /**
     * 获取团队成员
     * @param teamId 团队ID
     * @return 团队成员信息列表
     */
    ApiResponse<List<GetMemberDTO>> getTeamMembers(Integer teamId);

    /**
     * 添加团队成员
     * @param teamId 团队ID
     * @param member 成员信息
     * @return 是否添加成功
     */
    ApiResponse<Object> addTeamMember(Integer teamId, Member member);

    /**
     * 获取团队列表
     * @return 团队列表
     */
    ApiResponse<List<TeamDTO>> getTeamList();

    /**
     * 删除团队成员
     * @param teamId 团队ID
     * @param memberId 成员ID
     * @return 是否删除成功
     */
    ApiResponse<Object> deleteTeamMember(Integer teamId, Integer memberId);

    /**
     * 更新团队成员
     * @param teamId 团队ID
     * @param member 成员信息
     * @return 是否更新成功
     */
    ApiResponse<Object> updateTeamMember(Integer teamId, Member member);
}
