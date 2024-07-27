package com.gyeryongbrother.pickandtest.domain.service.dto;

import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januarySecond;

import java.util.List;

public class StockPriceResponseFixture {

    public static List<StockPriceResponse> stockPriceResponses() {
        return List.of(
                new StockPriceResponse(januaryFirst(), oneHundred()),
                new StockPriceResponse(januarySecond(), twoHundred())
        );
    }
}
