package com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private static final String SECRET_KEY = "my-secret-key-for-the-project-pickandtest";
    private static final Key key = hmacShaKeyFor(SECRET_KEY.getBytes(UTF_8));
    private static final long ONE_DAY = 1000 * 60 * 60;
    private static final long ONE_WEEK = 1000 * 60 * 60 * 7;

    public String generateAccessToken(String subject, List<String> authorities) {
        return generateToken(subject, authorities, ONE_DAY);
    }

    private String generateToken(
            String subject,
            List<String> authorities,
            long expiration
    ) {
        return Jwts.builder()
                .setSubject(subject)
                .claim("authorities", authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String subject, List<String> authorities) {
        return generateToken(subject, authorities, ONE_WEEK);
    }
}
