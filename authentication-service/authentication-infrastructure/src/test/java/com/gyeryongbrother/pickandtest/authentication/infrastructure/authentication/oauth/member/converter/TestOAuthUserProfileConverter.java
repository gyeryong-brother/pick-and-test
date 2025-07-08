package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.converter;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthId;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthMember;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthType;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class TestOAuthUserProfileConverter implements OAuthUserProfileConverter {

    @Override
    public OAuthType getSupportedOAuthType() {
        return OAuthType.TEST_SUPPORTED;
    }

    @Override
    public OAuthMember convert(OAuth2User oAuth2User) {
        return new OAuthMember(
                new OAuthId(OAuthType.TEST_SUPPORTED, "oAuthId"),
                "nickname",
                "profileImageUrl"
        );
    }
}
