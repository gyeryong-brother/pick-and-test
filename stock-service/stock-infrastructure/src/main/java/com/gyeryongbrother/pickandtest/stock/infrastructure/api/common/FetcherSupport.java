package com.gyeryongbrother.pickandtest.stock.infrastructure.api.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class FetcherSupport {

    private final RestTemplate restTemplate;

    public <T> T get(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }
}
