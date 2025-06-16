package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.REQUEST_CONTEXT_ERROR;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RequestContext {

    private final Map<String, String> context;

    public RequestContext() {
        context = new HashMap<>();
    }

    public void put(String key, String value) {
        context.put(key, value);
    }

    public String get(String key) {
        return Optional.ofNullable(context.get(key))
                .orElseThrow(() -> new AuthenticationInfrastructureException(REQUEST_CONTEXT_ERROR));
    }
}
