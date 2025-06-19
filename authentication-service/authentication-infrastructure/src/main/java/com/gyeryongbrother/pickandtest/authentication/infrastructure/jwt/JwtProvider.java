package com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.nio.charset.StandardCharsets.UTF_8;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

    public String generateAccessToken(OAuth2User oAuth2User) {
        return generateToken(oAuth2User, ONE_DAY);
    }

    private String generateToken(OAuth2User oAuth2User, long expiration) {
        String subject = oAuth2User.getName();
        List<String> authorities = oAuth2User.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return generateToken(subject, authorities, expiration);
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

    public String generateRefreshToken(OAuth2User oAuth2User) {
        return generateToken(oAuth2User, ONE_WEEK);
    }
}
