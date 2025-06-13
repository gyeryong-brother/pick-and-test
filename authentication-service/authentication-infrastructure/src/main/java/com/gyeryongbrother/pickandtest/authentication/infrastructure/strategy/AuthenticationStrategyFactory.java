package com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy;

import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;

public interface AuthenticationStrategyFactory {

    AuthenticationStrategy resolve(AuthenticationAttempt authenticationAttempt);
}
