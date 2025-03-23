package com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.common;

import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.auth.AuthManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HeaderHandler {

    private static final String TRANSACTION_ID_HEADER_NAME = "tr_id";

    private final AuthManager authManager;

    public HttpHeaders getHeader(FetchType fetchType) {
        HttpHeaders httpHeaders = authManager.createAuthHttpHeaders();
        httpHeaders.set(TRANSACTION_ID_HEADER_NAME, fetchType.getTransactionId());
        return httpHeaders;
    }
}
