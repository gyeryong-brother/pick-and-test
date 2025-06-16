package com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.OAUTH_SERVER_NOT_SUPPORTED;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class OauthClientComposite {

    private final Map<AuthenticationMethod, OauthClient> oauthClientsByMethod;

    public OauthClientComposite(Set<OauthClient> oauthClients) {
        oauthClientsByMethod = oauthClients.stream()
                .collect(toMap(OauthClient::support, identity()));
    }

    public OauthClient oauthClient(AuthenticationMethod authenticationMethod) {
        return Optional.ofNullable(oauthClientsByMethod.get(authenticationMethod))
                .orElseThrow(() -> new AuthenticationInfrastructureException(OAUTH_SERVER_NOT_SUPPORTED));
    }
}
