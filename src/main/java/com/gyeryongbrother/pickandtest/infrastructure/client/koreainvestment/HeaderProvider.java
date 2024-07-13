package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth.AuthManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HeaderProvider {

    private static final String TRANSACTION_ID_HEADER_NAME = "tr_id";

    private final AuthManager authManager;

    public HttpHeaders getStockHeader() {
        HttpHeaders httpHeaders = authManager.createAuthHttpHeaders();
        httpHeaders.set(TRANSACTION_ID_HEADER_NAME, FetchType.STOCK.getTransactionId());
        return httpHeaders;
    }
}
