package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class RequestBody {

    private final MultiValueMap<String, String> body;

    protected RequestBody() {
        body = new LinkedMultiValueMap<>();
    }

    protected void add(String key, String value) {
        body.add(key, value);
    }

    public MultiValueMap<String, String> body() {
        return body;
    }
}
