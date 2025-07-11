package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.strategy;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.LoginType;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenRepository;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.jwt.JwtProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.HttpServletResponseFacade;
import org.springframework.stereotype.Component;

@Component
class RedirectStrategy extends ResponseStrategy {

    private static final String REDIRECT_URL = "http://pickandtest.com:3000/login/callback?token=";

    public RedirectStrategy(JwtProvider jwtProvider, RefreshTokenRepository refreshTokenRepository) {
        super(jwtProvider, refreshTokenRepository);
    }

    @Override
    public LoginType support() {
        return LoginType.OAUTH;
    }

    @Override
    protected void responseAccessToken(HttpServletResponseFacade responseFacade, String accessToken) {
        responseFacade.redirect(REDIRECT_URL + accessToken);
    }
}
