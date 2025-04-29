package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationContext;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;

public interface Authenticator {

    Tokens authenticate(AuthenticationContext authenticationContext);
}
