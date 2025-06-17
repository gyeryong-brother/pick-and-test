package com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;

public interface LoginPageUrlProvider extends Supportable<AuthenticationMethod> {

    String getLoginPageUrl();
}
