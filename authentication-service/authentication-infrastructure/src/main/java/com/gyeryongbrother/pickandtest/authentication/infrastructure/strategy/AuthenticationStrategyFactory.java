package com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationContext;

public interface AuthenticationStrategyFactory {

    AuthenticationStrategy resolve(AuthenticationContext authenticationContext);
}
