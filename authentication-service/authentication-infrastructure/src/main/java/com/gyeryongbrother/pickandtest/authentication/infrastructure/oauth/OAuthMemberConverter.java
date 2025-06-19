package com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthType;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface OAuthMemberConverter {

    OAuthType support();

    OAuthMember convert(OAuth2User oAuth2User);
}
