package com.demo.spring_demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    private static final String SECRET = "your-secret-key";
    // token duration (ms)
    private static final long EXPIRATION = 24*60*60*1000; // One day

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
                .sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * Verify a token
     * @param token token string
     * @return decoded JWT
     */
    public DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
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