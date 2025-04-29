package com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        String token = resolveToken((HttpServletRequest) request);

        if (token != null && jwtProvider.validateToken(token)) {
            Authentication authentication = jwtProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        if ((token == null) | (token != null && !jwtProvider.validateToken(token))) {
            String refreshToken = getRefreshTokenFromCookies((HttpServletRequest) request);
            if (refreshToken != null && jwtProvider.validateToken(refreshToken)) {
                Long memberId = jwtProvider.getMemberIdFromToken(refreshToken);
                MemberRole memberRole = jwtProvider.getRoleFromToken(refreshToken);
                String newAccessToken = jwtProvider.generateAccessToken(memberId, memberRole);

                Authentication authentication = jwtProvider.getAuthentication(newAccessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                ((HttpServletResponse) response).setHeader("Authorization", "Bearer " + newAccessToken);
            }
        }

        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private String getRefreshTokenFromCookies(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }

        for (Cookie cookie : request.getCookies()) {
            if ("refreshToken".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
