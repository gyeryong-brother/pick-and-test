package com.gyeryongbrother.pickandtest.infrastructure.config;

import java.io.IOException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(@NonNull ClientHttpResponse response) {
        HttpStatusCode statusCode = getStatusCode(response);
        return statusCode.is4xxClientError() || statusCode.is5xxServerError();
    }

    private HttpStatusCode getStatusCode(ClientHttpResponse response) {
        try {
            return response.getStatusCode();
        } catch (IOException exception) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void handleError(@NonNull ClientHttpResponse response) {
        HttpStatusCode statusCode = getStatusCode(response);
        if (statusCode.is4xxClientError()) {
            throw new IllegalArgumentException("client error");
        }
        if (statusCode.is5xxServerError()) {
            throw new IllegalStateException("server error");
        }
    }
}
