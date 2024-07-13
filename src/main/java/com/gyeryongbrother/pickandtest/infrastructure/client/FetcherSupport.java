package com.gyeryongbrother.pickandtest.infrastructure.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class FetcherSupport {

    private final RestTemplate restTemplate;

    public <T> T get(
            String url,
            HttpHeaders httpHeaders,
            Class<T> responseType
    ) {
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<T> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                responseType
        );
        return responseEntity.getBody();
    }

    public <T> T post(
            String url,
            Object request,
            Class<T> responseType
    ) {
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(
                url,
                request,
                responseType
        );
        return responseEntity.getBody();
    }
}
