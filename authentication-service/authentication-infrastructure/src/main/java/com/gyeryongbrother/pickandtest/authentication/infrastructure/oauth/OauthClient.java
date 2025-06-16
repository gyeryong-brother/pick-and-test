package com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.Supportable;

public interface OauthClient extends Supportable<AuthenticationMethod> {

    OauthMember fetchMember(String authorizationCode);
}
