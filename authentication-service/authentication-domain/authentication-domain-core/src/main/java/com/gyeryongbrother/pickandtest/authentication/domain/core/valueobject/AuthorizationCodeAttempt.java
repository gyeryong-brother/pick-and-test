package com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject;

import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationCodeAttempt implements AuthenticationAttempt {

    private final AuthenticationMethod authenticationMethod;
    private final String code;

    @Override
    public AuthenticationMethod method() {
        return authenticationMethod;
    }

    @Override
    public String principal() {
        return null;
    }

    @Override
    public String credentials() {
        return code;
    }
}
