package com.gyeryongbrother.pickandtest.authentication.infrastructure.security;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.CREATE_BODY_FAILED;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.JwtProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.Tokenizable;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.security.dto.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
        Tokenizable tokenizable = (Tokenizable) authentication.getPrincipal();
        HttpServletResponseFacade responseFacade = new HttpServletResponseFacade(response);
        responseFacade.addBody(createAccessTokenBody(tokenizable));
        String refreshToken = jwtProvider.generateRefreshToken(tokenizable);
        responseFacade.addRefreshTokenCookie(refreshToken);
    }

    private String createAccessTokenBody(Tokenizable tokenizable) {
        String accessToken = jwtProvider.generateAccessToken(tokenizable);
        try {
            return objectMapper.writeValueAsString(new LoginResponse(accessToken));
        } catch (JsonProcessingException e) {
            throw new AuthenticationInfrastructureException(CREATE_BODY_FAILED);
        }
    }
}
