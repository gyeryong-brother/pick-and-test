package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.token;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.token.dto.KakaoTokenResponse;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.kakao.KakaoOauthConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class TokenFetcher {

    private final RestTemplate restTemplate;
    private final KakaoOauthConfig kakaoOauthConfig;

    public KakaoTokenResponse fetchToken(String authorizationCode) {
        String url = UriComponentsBuilder.fromUriString("https://kauth.kakao.com/oauth/token")
                .toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoOauthConfig.clientId());
        body.add("redirect_uri", kakaoOauthConfig.redirectUri());
        body.add("code", authorizationCode);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        return restTemplate.postForEntity(url, request, KakaoTokenResponse.class)
                .getBody();
    }
}
