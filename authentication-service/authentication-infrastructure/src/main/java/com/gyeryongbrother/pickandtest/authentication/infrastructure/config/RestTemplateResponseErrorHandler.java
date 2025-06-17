package com.gyeryongbrother.pickandtest.authentication.infrastructure.config;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.API_FETCH_CLIENT_ERROR;
import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.API_FETCH_FAILED;
import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.API_FETCH_SERVER_ERROR;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
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
            throw new AuthenticationInfrastructureException(API_FETCH_FAILED);
        }
    }

    @Override
    public void handleError(@NonNull ClientHttpResponse response) throws IOException {
        HttpStatusCode statusCode = getStatusCode(response);

        String errorBody = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        System.out.println(errorBody);

        if (statusCode.is4xxClientError()) {
            throw new AuthenticationInfrastructureException(API_FETCH_CLIENT_ERROR);
        }
        if (statusCode.is5xxServerError()) {
            throw new AuthenticationInfrastructureException(API_FETCH_SERVER_ERROR);
        }
    }
}
