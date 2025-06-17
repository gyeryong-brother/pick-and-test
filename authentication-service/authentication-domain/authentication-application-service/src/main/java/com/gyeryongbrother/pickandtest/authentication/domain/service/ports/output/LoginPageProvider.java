package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;

public interface LoginPageProvider {

    String getLoginPageUrl(AuthenticationMethod authenticationMethod);
}
