package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.strategy;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.RefreshToken;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.LoginType;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenRepository;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.jwt.JwtProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.jwt.Tokenizable;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.HttpServletResponseFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public abstract class ResponseStrategy {

    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public void response(HttpServletResponseFacade responseFacade, Tokenizable tokenizable) {
        String accessToken = jwtProvider.generateAccessToken(tokenizable);
        responseAccessToken(responseFacade, accessToken);
        String refreshToken = jwtProvider.generateRefreshToken(tokenizable);
        refreshTokenRepository.save(new RefreshToken(Long.parseLong(tokenizable.subject()), refreshToken));
        responseFacade.addRefreshTokenCookie(refreshToken);
    }

    public abstract LoginType support();

    protected abstract void responseAccessToken(HttpServletResponseFacade responseFacade, String accessToken);
}
