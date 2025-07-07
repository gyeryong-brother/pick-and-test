package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OauthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthType;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.MemberClient;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.OauthCredentialQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.OauthCredentialRepository;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.OAuthMember;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.converter.OAuthUserProfileConverter;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.converter.OAuthUserProfileConverterProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuthUserService extends DefaultOAuth2UserService {

    private final OAuthUserProfileConverterProvider provider;
    private final MemberClient memberClient;
    private final OauthCredentialRepository oauthCredentialRepository;
    private final OauthCredentialQueryRepository oauthCredentialQueryRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuthUserProfileConverter converter = provider.getConverterByType(oAuthType(userRequest));
        OAuthMember oAuthMember = converter.convert(oAuth2User);
        OauthCredential oauthCredential = oauthCredentialQueryRepository.findByOauthId(oAuthMember.oauthId())
                .orElseGet(() -> registerMember(oAuthMember));
        return new CustomOAuth2User(oauthCredential, oAuth2User);
    }

    private OAuthType oAuthType(OAuth2UserRequest userRequest) {
        String oAuthType = userRequest.getClientRegistration().getRegistrationId();
        return OAuthType.from(oAuthType);
    }

    private OauthCredential registerMember(OAuthMember oAuthMember) {
        Member member = memberClient.registerMember(oAuthMember.toDomain());
        OauthCredential oauthCredential = new OauthCredential(member.id(), MemberRole.USER, oAuthMember.oauthId());
        return oauthCredentialRepository.save(oauthCredential);
    }
}
