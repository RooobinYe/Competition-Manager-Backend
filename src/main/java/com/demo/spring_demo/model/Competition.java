package com.demo.spring_demo.model;

import java.time.LocalDateTime;

public class Competition {
    private Long id;
    private String name;
    private String description;
    private Integer minTeamMembers;
    private Integer maxTeamMembers;
    /**
     * 活动负责人学工号
     */
    private String workCode;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime reviewBeginTime;
    private LocalDateTime reviewEndTime;
    private LocalDateTime createTime;
}
