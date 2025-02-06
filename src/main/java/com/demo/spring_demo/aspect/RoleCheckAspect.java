package com.demo.spring_demo.aspect;

import com.demo.spring_demo.annotation.RequireRole;
import jakarta.servlet.http.HttpServletRequest;
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
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String roleStr = (String) request.getAttribute("role");
        
        logger.info("Checking role. Required roles: {}, User role: {}", Arrays.toString(requireRole.value()), roleStr);
        
//        if (roleStr == null) {
//            logger.error("No role found in request");
//            throw new RuntimeException("Insufficient privileges: No role found");
//        }
////
//        try {
//            int userRole = Integer.parseInt(roleStr);
//            logger.info("User role (parsed): {}", userRole);
//            if (Arrays.stream(requireRole.value()).noneMatch(role -> role == userRole)) {
//                logger.error("Invalid role. User role {} is not in allowed roles: {}",
//                    userRole, Arrays.toString(requireRole.value()));
//                throw new RuntimeException("Insufficient privileges: Invalid role");
//            }
//            logger.info("Role check passed");
//        } catch (NumberFormatException e) {
//            logger.error("Failed to parse role: {}", roleStr, e);
//            throw new RuntimeException("Invalid role format");
//        }
    }
} 