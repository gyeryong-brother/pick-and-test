package com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import io.jsonwebtoken.Claims;

public interface JwtProvider {

    Tokens createTokens(Long memberId, MemberRole memberRole);

    String generateAccessToken(Long memberId, MemberRole role);

    String generateRefreshToken(Long memberId, MemberRole role);

    Claims extractClaims(String token);

    boolean validateToken(String token);

    Long getMemberIdFromToken(String token);

    MemberRole getRoleFromToken(String token);
}
