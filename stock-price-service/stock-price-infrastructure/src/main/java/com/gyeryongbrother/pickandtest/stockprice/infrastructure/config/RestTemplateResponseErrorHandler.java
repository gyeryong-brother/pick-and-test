package com.gyeryongbrother.pickandtest.stockprice.infrastructure.config;

import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureExceptionType.API_FETCH_CLIENT_ERROR;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureExceptionType.API_FETCH_FAILED;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureExceptionType.API_FETCH_SERVER_ERROR;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureException;
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
            throw new StockPriceInfrastructureException(API_FETCH_FAILED);
        }
    }

    @Override
    public void handleError(@NonNull ClientHttpResponse response) {
        HttpStatusCode statusCode = getStatusCode(response);
        if (statusCode.is4xxClientError()) {
            throw new StockPriceInfrastructureException(API_FETCH_CLIENT_ERROR);
        }
        if (statusCode.is5xxServerError()) {
            throw new StockPriceInfrastructureException(API_FETCH_SERVER_ERROR);
        }
    }
}
