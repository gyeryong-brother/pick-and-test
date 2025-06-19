package com.gyeryongbrother.pickandtest.authentication.infrastructure.security;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.CREATE_BODY_FAILED;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.JwtProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.security.dto.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        HttpServletResponseFacade responseFacade = new HttpServletResponseFacade(response);
        responseFacade.addAccessTokenBody(createAccessTokenBody(oAuth2User));
        String refreshToken = jwtProvider.generateRefreshToken(oAuth2User);
        responseFacade.addRefreshTokenCookie(refreshToken);
    }

    private String createAccessTokenBody(OAuth2User oAuth2User) {
        String accessToken = jwtProvider.generateAccessToken(oAuth2User);
        try {
            return objectMapper.writeValueAsString(new LoginResponse(accessToken));
        } catch (JsonProcessingException e) {
            throw new AuthenticationInfrastructureException(CREATE_BODY_FAILED);
        }
    }
}
