package com.gyeryongbrother.pickandtest.authentication.infrastructure.security;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.Tokenizable;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.response.HttpServletResponseFacade;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.response.ResponseStrategyComposite;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ResponseStrategyComposite responseStrategyComposite;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        Tokenizable tokenizable = (Tokenizable) authentication.getPrincipal();
        HttpServletResponseFacade responseFacade = new HttpServletResponseFacade(response);
        responseStrategyComposite.response(responseFacade, tokenizable);
    }
}
