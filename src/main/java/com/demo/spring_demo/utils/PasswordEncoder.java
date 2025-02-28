package com.demo.spring_demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 密码加密工具类
 * 使用BCrypt算法进行密码哈希存储
 */
@Component
public class PasswordEncoder {
    
    private final BCryptPasswordEncoder encoder;
    
    public PasswordEncoder() {
        // use default strength 10
        this.encoder = new BCryptPasswordEncoder();
    }
    
    /**
     * 对密码进行加密
     * @param rawPassword 原始密码
     * @return 加密后的密码
     */
    public String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }
    
    /**
     * 验证密码是否匹配
     * @param rawPassword 原始密码
     * @param encodedPassword 加密后的密码
     * @return 是否匹配
     */
    public boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
} 