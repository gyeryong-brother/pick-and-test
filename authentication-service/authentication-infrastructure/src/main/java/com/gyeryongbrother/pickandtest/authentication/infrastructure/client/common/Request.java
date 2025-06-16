package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common;

import java.util.Optional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public abstract class Request {

    private final HttpEntity<Object> httpEntity;

    public Request() {
        httpEntity = new HttpEntity<>(headers());
    }

    public Request(RequestBody requestBody) {
        httpEntity = new HttpEntity<>(requestBody.body(), headers());
    }

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        token().ifPresent(headers::setBearerAuth);
        mediaType().ifPresent(headers::setContentType);
        return headers;
    }

    protected abstract Optional<String> token();

    protected abstract Optional<MediaType> mediaType();

    public HttpEntity<Object> httpEntity() {
        return httpEntity;
    }
}
