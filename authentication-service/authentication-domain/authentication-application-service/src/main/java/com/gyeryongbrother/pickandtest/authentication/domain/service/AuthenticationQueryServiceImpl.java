package com.gyeryongbrother.pickandtest.authentication.domain.service;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input.AuthenticationQueryService;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.LoginPageProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationQueryServiceImpl implements AuthenticationQueryService {

    private final LoginPageProvider loginPageProvider;

    @Override
    public String getLoginPageUrl(AuthenticationMethod authenticationMethod) {
        return loginPageProvider.getLoginPageUrl(authenticationMethod);
    }
}
