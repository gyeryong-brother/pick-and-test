package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OAuthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthMember;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthType;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input.OAuthCredentialService;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.converter.OAuthUserProfileConverter;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.converter.OAuthUserProfileConverterProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuthUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final DefaultOAuth2UserService defaultOAuth2UserService;
    private final OAuthUserProfileConverterProvider profileConverterProvider;
    private final OAuthCredentialService oAuthCredentialService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuthType oAuthType = oAuthType(userRequest);
        OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(userRequest);
        OAuthMember oAuthMember = convert(oAuthType, oAuth2User);
        OAuthCredential oauthCredential = oAuthCredentialService.getOrRegisterOAuthCredential(oAuthMember);
        return new CustomOAuth2User(oauthCredential, oAuth2User);
    }

    private OAuthType oAuthType(OAuth2UserRequest userRequest) {
        String oAuthType = userRequest.getClientRegistration().getRegistrationId();
        return OAuthType.from(oAuthType);
    }

    private OAuthMember convert(OAuthType oAuthType, OAuth2User oAuth2User) {
        OAuthUserProfileConverter converter = profileConverterProvider.getConverterByType(oAuthType);
        return converter.convert(oAuth2User);
    }
}
