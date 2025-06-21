package com.gyeryongbrother.pickandtest.authentication.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.JwtProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.Tokenizable;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        HttpServletResponseFacade responseFacade = new HttpServletResponseFacade(response, "text/html");
        responseFacade.addBody(createAccessTokenBody(tokenizable));
        String refreshToken = jwtProvider.generateRefreshToken(tokenizable);
        responseFacade.addRefreshTokenCookie(refreshToken);
    }

    private String createAccessTokenBody(Tokenizable tokenizable) {
        String accessToken = jwtProvider.generateAccessToken(tokenizable);
        return "<script>" +
                "window.opener.postMessage({ accessToken: '" + accessToken + "' }, '*');" +
                "window.close();" +
                "</script>";
    }
}
