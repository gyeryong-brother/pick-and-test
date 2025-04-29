package com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface JwtProvider {

    String generateAccessToken(Long memberId, MemberRole role);

    String generateRefreshToken(Long memberId, MemberRole role);

    Claims extractClaims(String token);

    boolean validateToken(String token);

    Long getMemberIdFromToken(String token);

    MemberRole getRoleFromToken(String token);

    Authentication getAuthentication(String token);
}
