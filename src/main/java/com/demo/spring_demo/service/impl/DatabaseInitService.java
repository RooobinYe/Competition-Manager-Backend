package com.demo.spring_demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.boot.CommandLineRunner;
import com.demo.spring_demo.mapper.TeamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DatabaseInitService implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitService.class);
    
    @Autowired
    private TeamMapper teamMapper;
    
    @Override
    public void run(String... args) {
        try {
            logger.info("Initializing database tables...");
            teamMapper.createTeamTable();
            teamMapper.createTeamMemberTable();
            teamMapper.createTeamInstructorTable();
            logger.info("Database tables initialized successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize database tables", e);
        }
    }
} 