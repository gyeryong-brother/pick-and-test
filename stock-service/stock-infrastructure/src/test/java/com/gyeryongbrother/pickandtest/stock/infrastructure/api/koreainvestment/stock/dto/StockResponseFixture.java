package com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment.stock.dto;

public class StockResponseFixture {

    public static StockResponse appleStockResponse() {
        return new StockResponse(
                "0",
                "KIOK0530",
                "조회되었습니다                                                                  ",
                new StockDetail(
                        "APPLE INC",
                        "15334100000",
                        ""
                )
        );
    }
}
