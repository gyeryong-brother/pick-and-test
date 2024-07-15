package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto;

public class FetchStockResponseFixture {

    public static FetchStockResponse empty() {
        return new FetchStockResponse(
                "",
                "",
                "",
                new StockDetail("", "", "")
        );
    }

    public static FetchStockResponse actualFetchStockResponse() {
        return new FetchStockResponse(
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
