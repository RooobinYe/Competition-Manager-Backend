package com.demo.spring_demo.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.demo.spring_demo.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取 token
        String token = request.getHeader("Authorization");
        
        if (request.getRequestURI().contains("/login")) {
            return true;
        }

        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            response.getWriter().write("Unauthorized: No token provided");
            return false;
        }

        try {
            // Remove Bearer prefix
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            
            jwtUtils.verifyToken(token);
            
            // 将用户信息存入 request，方便后续使用
            request.setAttribute("userId", jwtUtils.getUserIdFromToken(token));
            request.setAttribute("role", jwtUtils.getRoleFromToken(token));
            
            return true;
        } catch (JWTVerificationException e) {
            response.setStatus(401);
            response.getWriter().write("Unauthorized: Invalid token"); // TODO 采用统一的 response 模板
            return false;
        }
    }
} 