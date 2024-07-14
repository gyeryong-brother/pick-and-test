package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FetchType {

    STOCK("CTPF1702R"),
    STOCK_PRICE("HHDFS76240000"),
    ;

    private final String transactionId;
}
