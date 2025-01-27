package com.demo.spring_demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DatabaseInitService implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitService.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void run(String... args) {
        try {
            logger.info("Initializing database tables...");
            
            // 执行schema.sql文件中的建表语句
            ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8", new ClassPathResource("db/schema.sql"));
            resourceDatabasePopulator.execute(jdbcTemplate.getDataSource());
            
            logger.info("Database tables initialized successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize database tables", e);
            throw e; // 重新抛出异常，确保应用启动失败时能够得到通知
        }
    }
} 