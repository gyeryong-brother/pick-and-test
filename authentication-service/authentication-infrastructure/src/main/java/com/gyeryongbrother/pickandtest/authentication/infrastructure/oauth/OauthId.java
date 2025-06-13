package com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OauthId {

    private final String oauthServerId;
    private final AuthenticationMethod authenticationMethod;

    public String oauthServerId() {
        return oauthServerId;
    }

    public AuthenticationMethod authenticationMethod() {
        return authenticationMethod;
    }
}
