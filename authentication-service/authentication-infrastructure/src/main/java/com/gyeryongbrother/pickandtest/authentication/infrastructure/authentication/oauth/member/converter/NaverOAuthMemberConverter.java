package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthId;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.OAuthMember;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class NaverOAuthMemberConverter implements OAuthMemberConverter {

    private static final String MEMBER_INFO_KEY = "response";
    private static final String NICKNAME_KEY = "name";
    private static final String PROFILE_IMAGE_URL_KEY = "profile_image";

    private final ObjectMapper objectMapper;

    @Override
    public OAuthType support() {
        return OAuthType.NAVER;
    }

    @Override
    public OAuthMember convert(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map<String, String> memberInfo = objectMapper.convertValue(attributes.get(MEMBER_INFO_KEY),
                new TypeReference<>() {
                });
        return new OAuthMember(
                new OAuthId(OAuthType.NAVER, memberInfo.get("id")),
                memberInfo.get(NICKNAME_KEY),
                memberInfo.get(PROFILE_IMAGE_URL_KEY)
        );
    }
}
