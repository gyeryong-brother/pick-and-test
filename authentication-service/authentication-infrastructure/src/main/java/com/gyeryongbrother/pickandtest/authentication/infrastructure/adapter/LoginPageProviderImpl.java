package com.gyeryongbrother.pickandtest.authentication.infrastructure.adapter;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.OAUTH_SERVER_NOT_SUPPORTED;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.LoginPageProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.Supportable;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.LoginPageUrlProvider;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class LoginPageProviderImpl implements LoginPageProvider {

    private final Map<AuthenticationMethod, LoginPageUrlProvider> providersByMethod;

    public LoginPageProviderImpl(Set<LoginPageUrlProvider> providers) {
        providersByMethod = providers.stream()
                .collect(toMap(Supportable::support, identity()));
    }

    @Override
    public String getLoginPageUrl(AuthenticationMethod authenticationMethod) {
        return Optional.ofNullable(providersByMethod.get(authenticationMethod))
                .orElseThrow(() -> new AuthenticationInfrastructureException(OAUTH_SERVER_NOT_SUPPORTED))
                .getLoginPageUrl();
    }
}
