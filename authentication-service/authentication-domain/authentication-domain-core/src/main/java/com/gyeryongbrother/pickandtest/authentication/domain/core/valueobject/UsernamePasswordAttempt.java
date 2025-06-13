package com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject;

import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsernamePasswordAttempt implements AuthenticationAttempt {

    private final String username;
    private final String password;

    @Override
    public AuthenticationMethod method() {
        return AuthenticationMethod.GYERYONG_BROTHER;
    }

    @Override
    public String principal() {
        return username;
    }

    @Override
    public String credentials() {
        return password;
    }
}
