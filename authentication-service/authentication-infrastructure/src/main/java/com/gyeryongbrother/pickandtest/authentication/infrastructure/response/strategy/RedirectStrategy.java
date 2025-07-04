package com.gyeryongbrother.pickandtest.authentication.infrastructure.response.strategy;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.JwtProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.response.HttpServletResponseFacade;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.security.LoginType;
import org.springframework.stereotype.Component;

@Component
public class RedirectStrategy extends ResponseStrategy {

    private static final String REDIRECT_URL = "http://localhost:3000/login/callback?token=";

    public RedirectStrategy(JwtProvider jwtProvider) {
        super(jwtProvider);
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
