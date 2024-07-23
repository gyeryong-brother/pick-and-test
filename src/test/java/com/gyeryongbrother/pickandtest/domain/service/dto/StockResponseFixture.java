package com.gyeryongbrother.pickandtest.domain.service.dto;

public class StockResponseFixture {

    public static StockResponse stockResponse(String name, String symbol) {
        return new StockResponse(null, name, symbol);
    }
}
