package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.common;

import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureExceptionType.HEADER_CAN_NOT_BE_NULL;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureExceptionType.HEADER_SHOULD_HAVE_ONLY_ONE_VALUE;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.auth.AuthManager;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.ContinuityCode;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureException;
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
            throw new StockPriceInfrastructureException(HEADER_CAN_NOT_BE_NULL);
        }
        if (headerValues.size() != 1) {
            throw new StockPriceInfrastructureException(HEADER_SHOULD_HAVE_ONLY_ONE_VALUE);
        }
        String code = headerValues.get(0);
        return ContinuityCode.from(code);
    }
}
