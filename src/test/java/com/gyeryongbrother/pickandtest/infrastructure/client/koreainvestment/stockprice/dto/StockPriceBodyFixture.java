package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto;

import java.util.List;

public class StockPriceBodyFixture {

    public static StockPriceBody empty() {
        return new StockPriceBody("", "", "", List.of());
    }

    public static StockPriceBody firstStockPriceBody() {
        return new StockPriceBody("0", "MCA00000", "정상처리 되었습니다.", List.of(
                new StockPriceDetail("20240712", "230.5400"),
                new StockPriceDetail("20240711", "227.5700"),
                new StockPriceDetail("20240710", "232.9800")
        ));
    }

    public static StockPriceBody secondStockPriceBody() {
        return new StockPriceBody("0", "MCA00000", "정상처리 되었습니다.", List.of(
                new StockPriceDetail("20240709", "228.6800"),
                new StockPriceDetail("20240708", "227.8200"),
                new StockPriceDetail("20240705", "226.3400")
        ));
    }

    public static StockPriceBody thirdStockPriceBody() {
        return new StockPriceBody("0", "MCA00000", "정상처리 되었습니다.", List.of(
                new StockPriceDetail("20240703", "221.5500"),
                new StockPriceDetail("20240702", "220.2700")
        ));
    }
}
