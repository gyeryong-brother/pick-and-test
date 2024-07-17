package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class HeaderProvider {

    private static final String USER_AGENT_HEADER_NAME = "User-Agent";
    private static final String USER_AGENT_HEADER_VALUE = "PostmanRuntime/7.40.0";

    public HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(USER_AGENT_HEADER_NAME, USER_AGENT_HEADER_VALUE);
        return httpHeaders;
    }
}
