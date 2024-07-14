package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto;

import java.util.List;

public class StockPriceBodyFixture {

    public static StockPriceBody empty() {
        return new StockPriceBody(
                "",
                "",
                "",
                List.of()
        );
    }
}
