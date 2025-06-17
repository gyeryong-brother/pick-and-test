package com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.kakao;

import static com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod.KAKAO;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.LoginPageUrlProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class KakaoApiUrlProvider implements LoginPageUrlProvider {

    private static final String AUTH_DOMAIN = "https://kauth.kakao.com";
    private static final String API_DOMAIN = "https://kapi.kakao.com";

    private final KakaoApiConfig kakaoApiConfig;

    @Override
    public AuthenticationMethod support() {
        return KAKAO;
    }

    @Override
    public String getLoginPageUrl() {
        return UriComponentsBuilder.fromHttpUrl(AUTH_DOMAIN)
                .path("/oauth")
                .path("/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", kakaoApiConfig.clientId())
                .queryParam("redirect_uri", kakaoApiConfig.redirectUri())
                .queryParam("scope", String.join(",", kakaoApiConfig.scope()))
                .toUriString();
    }

    public String getTokenApiUrl() {
        return UriComponentsBuilder.fromHttpUrl(AUTH_DOMAIN)
                .path("/oauth")
                .path("/token")
                .toUriString();
    }

    public String getProfileApiUrl() {
        return UriComponentsBuilder.fromHttpUrl(API_DOMAIN)
                .path("/v2")
                .path("/user")
                .path("/me")
                .toUriString();
    }
}
