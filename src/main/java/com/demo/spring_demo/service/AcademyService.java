package com.demo.spring_demo.service;

import java.util.List;
import com.demo.spring_demo.model.Team;

public interface AcademyService {
    List<Team> getTeam(String academyId);
}
