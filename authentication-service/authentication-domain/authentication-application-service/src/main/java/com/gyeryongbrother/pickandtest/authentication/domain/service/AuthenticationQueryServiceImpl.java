package com.gyeryongbrother.pickandtest.authentication.domain.service;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginPageResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input.AuthenticationQueryService;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.Authenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationQueryServiceImpl implements AuthenticationQueryService {

    private final Authenticator authenticator;

    @Override
    public LoginPageResponse getLoginPage(AuthenticationMethod authenticationMethod) {
        String url = authenticator.getLoginPage(authenticationMethod);
        return new LoginPageResponse(url);
    }
}
