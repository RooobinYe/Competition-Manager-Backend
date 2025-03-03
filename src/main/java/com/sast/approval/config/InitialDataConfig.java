package com.sast.approval.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sast.approval.mapper.UserMapper;
import com.sast.approval.model.User;
import com.sast.approval.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 应用启动时的初始化配置
 * 主要用于创建默认管理员账户
 */
@Configuration
public class InitialDataConfig {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 应用启动时执行
     */
    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getRole, 3);
            Long adminCount = userMapper.selectCount(queryWrapper);

            if (adminCount == 0) {
                User admin = new User();
                admin.setUserCode("0");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setName("系统管理员");
                admin.setRole(3);
                admin.setAcademyId(0L);
                
                userMapper.insert(admin);
            }
        };
    }
} 