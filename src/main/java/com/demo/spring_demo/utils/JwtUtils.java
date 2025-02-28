package com.demo.spring_demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    private static final String JWT_SECRET_FILE = "jwt_secret.txt";
    private static final String DEFAULT_SECRET = "secret-key"; // 仅作为文件不存在时的后备选项
    // token duration (ms)
    private static final long EXPIRATION = 24*60*60*1000; // One day
    
    private String getSecret() {
        try {
            // 简单地从文件读取密钥
            try (BufferedReader reader = new BufferedReader(new FileReader(JWT_SECRET_FILE))) {
                String secret = reader.readLine();
                if (secret != null && !secret.isEmpty()) {
                    System.out.println("Using secret from file: " + secret);
                    return secret;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading JWT secret file: " + e.getMessage());
        }
        // 文件不存在或读取失败时使用默认密钥
        System.out.println("Using default secret: " + DEFAULT_SECRET);
        return DEFAULT_SECRET;
    }

    /**
     * Generate a token
     * @param userId user ID
     * @param role user role
     * @return token string
     */
    public String generateToken(String userId, String role) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION);
        
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        
        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("role", role)
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .sign(Algorithm.HMAC256(getSecret()));
    }

    /**
     * Verify a token
     * @param token token string
     * @return decoded JWT
     */
    public DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(getSecret())).build();
        return verifier.verify(token);
    }

    /**
     * Get user ID from token
     */
    public String getUserIdFromToken(String token) {
        DecodedJWT jwt = verifyToken(token);
        return jwt.getClaim("userId").asString();
    }

    /**
     * Get user role from token
     */
    public String getRoleFromToken(String token) {
        DecodedJWT jwt = verifyToken(token);
        return jwt.getClaim("role").asString();
    }
} 