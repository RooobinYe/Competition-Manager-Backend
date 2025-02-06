package com.demo.spring_demo.config;

import com.demo.spring_demo.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")     // 拦截所有请求
                .excludePathPatterns(       // 排除不需要拦截的请求
                        "/login",           // 登录接口
                        "/register",        // 注册接口
                        "/error"            // 错误页面
                );
    }
} 