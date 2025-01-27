package com.demo.spring_demo.service;

import com.demo.spring_demo.model.Team;
import com.demo.spring_demo.model.Member;
import com.demo.spring_demo.model.dto.captain.UpdateTeamDTO;
import com.demo.spring_demo.model.dto.captain.GetMemberDTO;
import com.demo.spring_demo.model.ApiResponse;
import java.util.List;

public interface CaptainService {

    /**
     * 更新团队信息
     * @param team 团队信息
     * @return 更新后的团队信息
     */
    ApiResponse<UpdateTeamDTO> updateTeamInfo(Team team);

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
    ApiResponse<Void> addTeamMember(Integer teamId, Member member);

}
