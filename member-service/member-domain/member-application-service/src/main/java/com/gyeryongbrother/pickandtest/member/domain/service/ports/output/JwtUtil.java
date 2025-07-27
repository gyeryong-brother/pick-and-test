package com.gyeryongbrother.pickandtest.member.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.member.domain.core.UserRole;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface JwtUtil {

    String generateAccessToken(Long memberId, UserRole role);

    String generateRefreshToken(Long memberId);

    Claims extractClaims(String token);

    boolean validateToken(String token);

    Long getMemberIdFromToken(String token);

    UserRole getRoleFromToken(String token);

    Authentication getAuthentication(String token);
}
