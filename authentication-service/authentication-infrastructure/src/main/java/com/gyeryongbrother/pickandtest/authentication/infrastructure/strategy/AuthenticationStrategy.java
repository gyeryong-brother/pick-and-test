package com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy;

import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import java.util.Set;

public interface AuthenticationStrategy {

    Set<AuthenticationMethod> supports();

    Tokens authenticate(AuthenticationAttempt authenticationAttempt);
}
