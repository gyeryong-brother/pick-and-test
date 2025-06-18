package com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomOAuthUserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("oAuth2User: {}", oAuth2User);
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String oauthId = String.valueOf(attributes.get("id"));
        String oauthType = userRequest.getClientRegistration().getRegistrationId();
        log.info("oAuthId: {}", oauthId);
        log.info("oAuthType: {}", oauthType);
        return oAuth2User;
    }
}
