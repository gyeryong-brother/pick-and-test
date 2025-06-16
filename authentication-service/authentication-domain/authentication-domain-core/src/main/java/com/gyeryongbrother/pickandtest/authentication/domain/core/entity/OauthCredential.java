package com.gyeryongbrother.pickandtest.authentication.domain.core.entity;

import com.gyeryongbrother.pickandtest.authentication.domain.core.model.Matcher;
import com.gyeryongbrother.pickandtest.authentication.domain.core.model.RegisteredCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OauthCredential implements RegisteredCredential {

    private final Long id;
    private final Long memberId;
    private final String oauthId;
    private final AuthenticationMethod authenticationMethod;

    @Override
    public AuthenticationMethod method() {
        return authenticationMethod;
    }

    @Override
    public Long principal() {
        return memberId;
    }

    @Override
    public boolean matches(Matcher matcher) {
        return matcher.matches(oauthId);
    }

    public Long id() {
        return id;
    }

    public Long memberId() {
        return memberId;
    }

    public String oauthId() {
        return oauthId;
    }

    public AuthenticationMethod authenticationMethod() {
        return authenticationMethod;
    }
}
