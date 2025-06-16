package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.naver;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthClient;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthMember;
import org.springframework.stereotype.Component;

@Component
public class NaverClient implements OauthClient {

    @Override
    public AuthenticationMethod support() {
        return AuthenticationMethod.NAVER;
    }

    @Override
    public OauthMember fetchMember(String authorizationCode) {
        return null;
    }
}
