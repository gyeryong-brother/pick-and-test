package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.jwt;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.nio.charset.StandardCharsets.UTF_8;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private static final long ONE_DAY = 1000 * 60 * 60;
    private static final long ONE_WEEK = 1000 * 60 * 60 * 7;

    private final Key key;

    public JwtProvider(
            @Value("${jwt.secret-key}") String secretKey
    ) {
        key = hmacShaKeyFor(secretKey.getBytes(UTF_8));
    }

    public String generateAccessToken(Tokenizable tokenizable) {
        return generateToken(tokenizable, ONE_DAY);
    }

    private String generateToken(
            Tokenizable tokenizable,
            long expiration
    ) {
        return Jwts.builder()
                .setSubject(tokenizable.subject())
                .claim("authorities", tokenizable.authorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Tokenizable tokenizable) {
        return generateToken(tokenizable, ONE_WEEK);
    }
}
