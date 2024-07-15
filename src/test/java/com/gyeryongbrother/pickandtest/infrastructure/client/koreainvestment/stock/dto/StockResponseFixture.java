package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto;

public class StockResponseFixture {

    public static StockResponse empty() {
        return new StockResponse(
                "",
                "",
                "",
                new StockDetail("", "", "")
        );
    }

    public static StockResponse actualStockResponse() {
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
