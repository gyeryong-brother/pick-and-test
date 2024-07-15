package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.ContinuityCode;

public class FetchStockPriceResponseFixture {

    public static StockPriceResponse firstStockPriceResponse() {
        return new StockPriceResponse(ContinuityCode.NEXT, StockPriceBodyFixture.firstStockPriceBody());
    }

    public static StockPriceResponse secondStockPriceResponse() {
        return new StockPriceResponse(ContinuityCode.NEXT, StockPriceBodyFixture.secondStockPriceBody());
    }

    public static StockPriceResponse thirdStockPriceResponse() {
        return new StockPriceResponse(ContinuityCode.END, StockPriceBodyFixture.thirdStockPriceBody());
    }
}
