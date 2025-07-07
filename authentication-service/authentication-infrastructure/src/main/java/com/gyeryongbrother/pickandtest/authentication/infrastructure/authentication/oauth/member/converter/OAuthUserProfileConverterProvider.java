package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.converter;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.OAUTH_SERVER_NOT_SUPPORTED;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class OAuthUserProfileConverterProvider {

    private final Map<OAuthType, OAuthUserProfileConverter> convertersByType;

    public OAuthUserProfileConverterProvider(Set<OAuthUserProfileConverter> converters) {
        convertersByType = converters.stream()
                .collect(toMap(OAuthUserProfileConverter::getSupportedOAuthType, identity()));
    }

    public OAuthUserProfileConverter getConverterByType(OAuthType oAuthType) {
        return Optional.ofNullable(convertersByType.get(oAuthType))
                .orElseThrow(() -> new AuthenticationInfrastructureException(OAUTH_SERVER_NOT_SUPPORTED));
    }
}
