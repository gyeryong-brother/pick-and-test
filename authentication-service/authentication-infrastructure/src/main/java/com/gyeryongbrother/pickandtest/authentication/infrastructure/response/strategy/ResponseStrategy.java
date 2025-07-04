package com.gyeryongbrother.pickandtest.authentication.infrastructure.response.strategy;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.JwtProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.Tokenizable;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.response.HttpServletResponseFacade;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.security.LoginType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public abstract class ResponseStrategy {

    private final JwtProvider jwtProvider;

    public void response(HttpServletResponseFacade responseFacade, Tokenizable tokenizable) {
        String accessToken = jwtProvider.generateAccessToken(tokenizable);
        responseAccessToken(responseFacade, accessToken);
        String refreshToken = jwtProvider.generateRefreshToken(tokenizable);
        responseFacade.addRefreshTokenCookie(refreshToken);
    }

    public abstract LoginType support();

    protected abstract void responseAccessToken(HttpServletResponseFacade responseFacade, String accessToken);
}
