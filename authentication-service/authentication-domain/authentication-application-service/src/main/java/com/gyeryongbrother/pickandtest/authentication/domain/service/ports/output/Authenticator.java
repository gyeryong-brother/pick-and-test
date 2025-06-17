package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;

public interface Authenticator {

    Tokens authenticate(AuthenticationAttempt authenticationAttempt);

    String getLoginPage(AuthenticationMethod authenticationMethod);
}
