package com.demo.spring_demo.model.dto.admin;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class competitionDTO implements Serializable {
    private String name;
    private String description;
    private Integer minTeamMembers;
    private Integer maxTeamMembers;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime reviewBeginTime;
    private LocalDateTime reviewEndTime;
}
