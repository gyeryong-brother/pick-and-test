package com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.kakao;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.kakao.dto.KakaoProfileResponse;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.kakao.dto.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class KakaoApiFetcher {

    private final KakaoApiUrlProvider kakaoApiUrlProvider;
    private final RestTemplate restTemplate;
    private final KakaoApiConfig kakaoApiConfig;

    public KakaoTokenResponse fetchToken(String authorizationCode) {
        String url = kakaoApiUrlProvider.getTokenApiUrl();
        return restTemplate.postForEntity(url, body(authorizationCode), KakaoTokenResponse.class)
                .getBody();
    }

    private HttpEntity<MultiValueMap<String, String>> body(String authorizationCode) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoApiConfig.clientId());
        body.add("redirect_uri", kakaoApiConfig.redirectUri());
        body.add("code", authorizationCode);
        body.add("client_secret", kakaoApiConfig.clientSecret());
        return new HttpEntity<>(body, formUrlEncoded());
    }

    private HttpHeaders formUrlEncoded() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_FORM_URLENCODED);
        return headers;
    }

    public KakaoProfileResponse fetchProfile(String accessToken) {
        String url = kakaoApiUrlProvider.getProfileApiUrl();
        return restTemplate.exchange(url, GET, authHeader(accessToken), KakaoProfileResponse.class)
                .getBody();
    }

    private HttpEntity<Object> authHeader(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(headers);
    }
}
