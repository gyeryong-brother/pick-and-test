package com.gyeryongbrother.pickandtest.authentication.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationContext;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.Authenticator;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy.AuthenticationStrategy;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy.AuthenticationStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticatorImpl implements Authenticator {

    private final AuthenticationStrategyFactory authenticationStrategyFactory;

    @Override
    public Tokens authenticate(AuthenticationContext authenticationContext) {
        AuthenticationStrategy authenticationStrategy = authenticationStrategyFactory.resolve(authenticationContext);
        return authenticationStrategy.authenticate(authenticationContext);
    }
}
