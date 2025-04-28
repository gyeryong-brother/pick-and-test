package com.gyeryongbrother.pickandtest.stock.infrastructure.config;

import static com.gyeryongbrother.pickandtest.stock.infrastructure.exception.StockInfrastructureExceptionType.API_FETCH_CLIENT_ERROR;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.exception.StockInfrastructureExceptionType.API_FETCH_FAILED;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.exception.StockInfrastructureExceptionType.API_FETCH_SERVER_ERROR;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.exception.StockInfrastructureExceptionType.BODY_READ_FAILED;

import com.gyeryongbrother.pickandtest.stock.infrastructure.exception.StockInfrastructureException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Slf4j
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
            throw new StockInfrastructureException(API_FETCH_FAILED);
        }
    }

    @Override
    public void handleError(@NonNull ClientHttpResponse response) {
        HttpStatusCode statusCode = getStatusCode(response);
        log.error(getBody(response));
        if (statusCode.is4xxClientError()) {
            throw new StockInfrastructureException(API_FETCH_CLIENT_ERROR);
        }
        if (statusCode.is5xxServerError()) {
            throw new StockInfrastructureException(API_FETCH_SERVER_ERROR);
        }
    }

    private String getBody(ClientHttpResponse response) {
        StringBuilder body = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
            return body.toString();
        } catch (IOException e) {
            throw new StockInfrastructureException(BODY_READ_FAILED);
        }
    }
}
