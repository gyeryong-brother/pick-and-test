package com.gyeryongbrother.pickandtest.member.infrastructure;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.core.UserRole;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.JwtUtil;
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
public class JwtUtilImpl implements JwtUtil {
    private static final String SECRET_KEY = "my-secret-key-for-the-project-pickandtest";
    private static final long ACCESS_TOKEN_EXPIRATION = 1000 * 1;
    private static final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Long memberId, UserRole role) {
        return Jwts.builder()
                .setSubject(String.valueOf(memberId))
                .claim("role", role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Long memberId) {
        return Jwts.builder()
                .setSubject(String.valueOf(memberId))
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

    public UserRole getRoleFromToken(String token) {
        String roleName = extractClaims(token).get("role", String.class);
        return UserRole.fromString(roleName);
    }

    @Override
    public Authentication getAuthentication(String token) {
        validateToken(token);
        UserRole userRole = getRoleFromToken(token);
        Long memberId = getMemberIdFromToken(token);

        Member member = Member.builder()
                .id(memberId)
                .userRole(userRole)
                .build();

        UserDetails principal = new UserDetailsImpl(member);

        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }

}
