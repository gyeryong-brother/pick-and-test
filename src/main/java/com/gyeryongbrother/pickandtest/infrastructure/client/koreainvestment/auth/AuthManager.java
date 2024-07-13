package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthManager {

    private static final String APP_KEY_HEADER_NAME = "appkey";
    private static final String APP_SECRET_HEADER_NAME = "appsecret";

    private final AccessTokenManager accessTokenManager;
    private final ClientCredential clientCredential;

    public HttpHeaders createAuthHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        String accessToken = accessTokenManager.getAccessToken();
        httpHeaders.setBearerAuth(accessToken);
        httpHeaders.set(APP_KEY_HEADER_NAME, clientCredential.getAppKey());
        httpHeaders.set(APP_SECRET_HEADER_NAME, clientCredential.getAppSecret());
        return httpHeaders;
    }
}
