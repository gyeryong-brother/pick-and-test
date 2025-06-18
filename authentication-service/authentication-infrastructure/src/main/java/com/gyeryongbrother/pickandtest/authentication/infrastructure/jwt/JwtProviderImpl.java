package com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtProviderImpl implements JwtProvider {

    private static final String SECRET_KEY = "my-secret-key-for-the-project-pickandtest";
    private static final long ACCESS_TOKEN_EXPIRATION = 1000 * 1;
    private static final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Tokens createTokens(Long memberId, MemberRole memberRole) {
        String accessToken = generateAccessToken(memberId, memberRole);
        String refreshToken = generateRefreshToken(memberId, memberRole);
        return new Tokens(memberId, accessToken, refreshToken);
    }

    public String generateAccessToken(Long memberId, MemberRole role) {
        return Jwts.builder()
                .setSubject(String.valueOf(memberId))
                .claim("role", role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Long memberId, MemberRole role) {
        return Jwts.builder()
                .setSubject(String.valueOf(memberId))
                .claim("role", role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            extractClaims(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getMemberIdFromToken(String token) {
        return Long.parseLong(extractClaims(token).getSubject());
    }

    public MemberRole getRoleFromToken(String token) {
        String roleName = extractClaims(token).get("role", String.class);
        return MemberRole.valueOf(roleName);
    }
}
