package com.gyeryongbrother.pickandtest.domain.service.dto;

import java.util.List;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januarySecond;

public class StockPriceResponseFixture {

    public static List<StockPriceResponse> stockPriceResponses() {
        return List.of(
                new StockPriceResponse(januaryFirst(), oneHundred()),
                new StockPriceResponse(januarySecond(), twoHundred())
        );
    }
}
