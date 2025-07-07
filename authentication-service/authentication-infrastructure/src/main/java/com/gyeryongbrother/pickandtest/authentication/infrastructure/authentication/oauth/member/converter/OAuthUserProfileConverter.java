package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.converter;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.OAuthMember;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface OAuthUserProfileConverter {

    OAuthType getSupportedOAuthType();

    OAuthMember convert(OAuth2User oAuth2User);
}
