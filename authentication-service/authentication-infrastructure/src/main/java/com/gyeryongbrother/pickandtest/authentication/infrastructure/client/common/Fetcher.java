package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.REQUEST_TYPE_ERROR;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class Fetcher {

    private final RestTemplate restTemplate;

    public <T> T fetch(
            HttpMethod httpMethod,
            Url url,
            Request request,
            Class<T> clazz
    ) {
        if (httpMethod == GET) {
            return get(url, request, clazz);
        }
        if (httpMethod == POST) {
            return post(url, request, clazz);
        }
        throw new AuthenticationInfrastructureException(REQUEST_TYPE_ERROR);
    }

    private <T> T post(Url url, Request request, Class<T> clazz) {
        ResponseEntity<T> response = restTemplate.postForEntity(url.value(), request.httpEntity(), clazz);
        return response.getBody();
    }

    private <T> T get(Url url, Request request, Class<T> clazz) {
        ResponseEntity<T> response = restTemplate.exchange(url.value(), GET, request.httpEntity(), clazz);
        return response.getBody();
    }
}
