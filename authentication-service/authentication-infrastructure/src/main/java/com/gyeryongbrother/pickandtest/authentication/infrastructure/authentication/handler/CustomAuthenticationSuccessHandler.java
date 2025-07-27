package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.handler;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.jwt.Tokenizable;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.HttpServletResponseFacade;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.strategy.ResponseStrategy;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.strategy.ResponseStrategyProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ResponseStrategyProvider responseStrategyProvider;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        Tokenizable tokenizable = (Tokenizable) authentication.getPrincipal();
        HttpServletResponseFacade responseFacade = new HttpServletResponseFacade(response);
        ResponseStrategy strategy = responseStrategyProvider.getStrategyByType(tokenizable.type());
        strategy.response(responseFacade, tokenizable);
    }
}
