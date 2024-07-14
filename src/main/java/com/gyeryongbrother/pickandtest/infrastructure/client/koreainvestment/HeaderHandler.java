package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth.AuthManager;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.ContinuityCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HeaderHandler {

    private static final String TRANSACTION_ID_HEADER_NAME = "tr_id";
    private static final String TRANSACTION_CONTINUITY = "tr_cont";

    private final AuthManager authManager;

    public HttpHeaders getHeader(FetchType fetchType) {
        HttpHeaders httpHeaders = authManager.createAuthHttpHeaders();
        httpHeaders.set(TRANSACTION_ID_HEADER_NAME, fetchType.getTransactionId());
        return httpHeaders;
    }

    public ContinuityCode parseContinuityCode(HttpHeaders httpHeaders) {
        List<String> headerValues = httpHeaders.get(TRANSACTION_CONTINUITY);
        if (headerValues == null) {
            throw new IllegalStateException("can not be null");
        }
        if (headerValues.size() != 1) {
            throw new IllegalStateException("should be only one value");
        }
        String code = headerValues.get(0);
        return ContinuityCode.from(code);
    }
}
