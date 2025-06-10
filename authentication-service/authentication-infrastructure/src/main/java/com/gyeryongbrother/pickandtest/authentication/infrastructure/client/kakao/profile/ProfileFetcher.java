package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.profile;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.profile.dto.KakaoProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class ProfileFetcher {

    private final RestTemplate restTemplate;

    public KakaoProfileResponse fetchProfile(String token) {
        String url = UriComponentsBuilder.fromUriString("https://kapi.kakao.com/v2/user/me")
                .toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<Object> request = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, request, KakaoProfileResponse.class)
                .getBody();
    }
}
