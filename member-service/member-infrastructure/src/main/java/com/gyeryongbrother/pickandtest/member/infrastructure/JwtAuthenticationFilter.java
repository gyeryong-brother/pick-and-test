package com.gyeryongbrother.pickandtest.member.infrastructure;

import com.gyeryongbrother.pickandtest.member.domain.core.UserRole;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.JwtUtil;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberQueryRepository;
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

    private final JwtUtil jwtUtil;
    private final MemberQueryRepository memberQueryRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = resolveToken((HttpServletRequest) request);

        if (token != null && jwtUtil.validateToken(token)) {
            Authentication authentication = jwtUtil.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        if ((token == null) | (token != null && !jwtUtil.validateToken(token))) {
            String refreshToken = getRefreshTokenFromCookies((HttpServletRequest) request);
            if (refreshToken != null && jwtUtil.validateToken(refreshToken)) {
                Long memberId = jwtUtil.getMemberIdFromToken(refreshToken);
                UserRole userRole = memberQueryRepository.findByMemberId(memberId).getUserRole();
                String newAccessToken = jwtUtil.generateAccessToken(memberId, userRole);

                Authentication authentication = jwtUtil.getAuthentication(newAccessToken);
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
