package com.gyeryongbrother.pickandtest.infrastructure.client;

import java.net.URI;
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

    public <T> T get(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }

    public <T> ResponseEntity<T> get(
            String url,
            HttpHeaders httpHeaders,
            Class<T> responseType
    ) {
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                responseType
        );
    }

    public <T> ResponseEntity<T> get(
            URI uri,
            HttpHeaders httpHeaders,
            Class<T> responseType
    ) {
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );
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
