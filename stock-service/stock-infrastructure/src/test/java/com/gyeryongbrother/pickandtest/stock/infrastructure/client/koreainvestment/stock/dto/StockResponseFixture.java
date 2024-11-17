package com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stock.dto;

public class StockResponseFixture {

    public static StockResponse appleStockResponse() {
        return stockResponse("APPLE INC", "15334100000");
    }

    public static StockResponse microsoftStockResponse() {
        return stockResponse("MICROSOFT CORP", "7432310000");
    }

    public static StockResponse nvidiaStockResponse() {
        return stockResponse("NVIDIA CORP", "24600000000");
    }

    private static StockResponse stockResponse(String productEnglishName, String listingStockNumber) {
        return new StockResponse(
                "0",
                "KIOK0530",
                "조회되었습니다                                                                  ",
                new StockDetail(
                        productEnglishName,
                        listingStockNumber,
                        ""
                )
        );
    }
}
