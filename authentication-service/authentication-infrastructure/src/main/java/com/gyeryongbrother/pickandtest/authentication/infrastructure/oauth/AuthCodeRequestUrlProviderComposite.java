package com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.OAUTH_SERVER_NOT_SUPPORTED;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class AuthCodeRequestUrlProviderComposite {

    private final Map<OauthServerType, AuthCodeRequestUrlProvider> providersByServerType;

    public AuthCodeRequestUrlProviderComposite(Set<AuthCodeRequestUrlProvider> providers) {
        providersByServerType = providers.stream()
                .collect(toMap(AuthCodeRequestUrlProvider::supportServer, identity()));
    }

    public String provide(OauthServerType oauthServerType) {
        return getProvider(oauthServerType).provide();
    }

    private AuthCodeRequestUrlProvider getProvider(OauthServerType oauthServerType) {
        return Optional.ofNullable(providersByServerType.get(oauthServerType))
                .orElseThrow(() -> new AuthenticationInfrastructureException(OAUTH_SERVER_NOT_SUPPORTED));
    }
}
