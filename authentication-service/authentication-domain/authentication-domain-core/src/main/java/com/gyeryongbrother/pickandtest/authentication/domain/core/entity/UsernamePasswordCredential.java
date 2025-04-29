package com.gyeryongbrother.pickandtest.authentication.domain.core.entity;

import com.gyeryongbrother.pickandtest.authentication.domain.core.model.Matcher;
import com.gyeryongbrother.pickandtest.authentication.domain.core.model.RegisteredCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;

public class UsernamePasswordCredential implements RegisteredCredential {

    private final Long id;
    private final Long memberId;
    private final String username;
    private final String password;

    public UsernamePasswordCredential(
            Long id,
            Long memberId,
            String username,
            String password
    ) {
        this.id = id;
        this.memberId = memberId;
        this.username = username;
        this.password = password;
    }

    @Override
    public AuthenticationMethod method() {
        return AuthenticationMethod.GYERYONG_BROTHER;
    }

    @Override
    public Long principal() {
        return memberId;
    }

    @Override
    public boolean matches(Matcher matcher) {
        return matcher.matches(password);
    }

    public Long id() {
        return id;
    }

    public Long memberId() {
        return memberId;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }
}
