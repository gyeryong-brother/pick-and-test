package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FetchType {

    STOCK("CTPF1702R"),
    ;

    private final String transactionId;
}
