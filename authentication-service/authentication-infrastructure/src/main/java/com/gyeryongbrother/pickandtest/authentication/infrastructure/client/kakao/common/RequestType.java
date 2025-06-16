package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.common;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

@RequiredArgsConstructor
public enum RequestType {

    KAKAO_TOKEN(POST),
    KAKAO_PROFILE(GET),
    ;

    private final HttpMethod httpMethod;

    public HttpMethod httpMethod() {
        return httpMethod;
    }
}
