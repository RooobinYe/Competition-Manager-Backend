package com.sast.approval.aspect;

import com.sast.approval.annotation.RequireRole;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.Arrays;

@Aspect
@Component
public class RoleCheckAspect {
    private static final Logger logger = LoggerFactory.getLogger(RoleCheckAspect.class);

    @Before("@annotation(requireRole)")
    public void checkRole(RequireRole requireRole) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new RuntimeException("当前请求上下文不可用");
        }
        
        String roleStr = (String) attributes.getRequest().getAttribute("role");
        if (roleStr == null) {
            logger.error("No role found in request");
            throw new RuntimeException("权限不足：未找到用户角色信息");
        }

        int userRole = Integer.parseInt(roleStr);
        if (Arrays.stream(requireRole.value()).noneMatch(role -> role == userRole)) {
            logger.error("Invalid role. User role {} is not in allowed roles: {}", userRole, Arrays.toString(requireRole.value()));
            throw new RuntimeException("权限不足：当前用户角色无权访问此接口");
        }
        
        logger.info("Role check passed for user role: {}", userRole);
    }
} 