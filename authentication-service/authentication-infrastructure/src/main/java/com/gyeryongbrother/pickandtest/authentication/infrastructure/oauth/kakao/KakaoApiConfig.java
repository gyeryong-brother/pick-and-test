package com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.kakao;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KakaoApiConfig {

    private final String clientId;
    private final String redirectUri;
    private final String clientSecret;
    private final List<String> scope;

    public KakaoApiConfig(
            @Value("${oauth.kakao.client_id}") String clientId,
            @Value("${oauth.kakao.redirect_uri}") String redirectUri,
            @Value("${oauth.kakao.client_secret}") String clientSecret,
            @Value("${oauth.kakao.scope}") List<String> scope
    ) {
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.clientSecret = clientSecret;
        this.scope = scope;
    }

    public String clientId() {
        return clientId;
    }

    public String redirectUri() {
        return redirectUri;
    }

    public String clientSecret() {
        return clientSecret;
    }

    public List<String> scope() {
        return scope;
    }
}
