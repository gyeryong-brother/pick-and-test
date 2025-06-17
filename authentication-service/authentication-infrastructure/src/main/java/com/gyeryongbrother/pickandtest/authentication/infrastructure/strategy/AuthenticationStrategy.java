package com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy;

import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.common.MultiSupportable;

public interface AuthenticationStrategy extends MultiSupportable<AuthenticationMethod> {

    Tokens authenticate(AuthenticationAttempt attempt);
}
