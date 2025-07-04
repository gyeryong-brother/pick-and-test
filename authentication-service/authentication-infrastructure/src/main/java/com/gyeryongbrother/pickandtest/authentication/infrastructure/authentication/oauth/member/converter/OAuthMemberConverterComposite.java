package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.converter;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.OAUTH_SERVER_NOT_SUPPORTED;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.OAuthMember;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class OAuthMemberConverterComposite {

    private final Map<OAuthType, OAuthMemberConverter> convertersByType;

    public OAuthMemberConverterComposite(Set<OAuthMemberConverter> converters) {
        convertersByType = converters.stream()
                .collect(toMap(OAuthMemberConverter::support, identity()));
    }

    public OAuthMember convert(OAuthType oAuthType, OAuth2User oAuth2User) {
        return Optional.ofNullable(convertersByType.get(oAuthType))
                .orElseThrow(() -> new AuthenticationInfrastructureException(OAUTH_SERVER_NOT_SUPPORTED))
                .convert(oAuth2User);
    }
}
