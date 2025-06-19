package com.gyeryongbrother.pickandtest.authentication.infrastructure.security;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

    }

    private void setResponse(HttpServletResponse response, OAuth2User oAuth2User) {
        String name = oAuth2User.getName();
        List<String> authorities = authorities(oAuth2User);
        String accessToken = jwtProvider.generateAccessToken(name, authorities);
        setAccessTokenBody();
        String refreshToken = jwtProvider.generateRefreshToken(name, authorities);
        setRefreshTokenCookie();
    }

    private List<String> authorities(OAuth2User oAuth2User) {
        return oAuth2User.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

    private void setAccessTokenBody() {
    }

    private void setRefreshTokenCookie() {
    }
}
