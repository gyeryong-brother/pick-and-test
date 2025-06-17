package com.gyeryongbrother.pickandtest.authentication.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.Authenticator;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.AuthCodeRequestUrlProviderComposite;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthServerType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy.AuthenticationStrategy;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy.AuthenticationStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticatorImpl implements Authenticator {

    private final AuthenticationStrategyFactory authenticationStrategyFactory;
    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;

    @Override
    public Tokens authenticate(AuthenticationAttempt authenticationAttempt) {
        AuthenticationStrategy authenticationStrategy = authenticationStrategyFactory.resolve(authenticationAttempt);
        return authenticationStrategy.authenticate(authenticationAttempt);
    }

    @Override
    public String getLoginPage(AuthenticationMethod authenticationMethod) {
        OauthServerType oauthServerType = OauthServerType.valueOf(authenticationMethod.name());
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }
}
