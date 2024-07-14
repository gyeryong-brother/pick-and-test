package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.ContinuityCode;

public class FetchStockPriceResponseFixture {

    public static FetchStockPriceResponse firstFetchStockPriceResponse() {
        return new FetchStockPriceResponse(ContinuityCode.NEXT, StockPriceBodyFixture.firstStockPriceBody());
    }

    public static FetchStockPriceResponse secondFetchStockPriceResponse() {
        return new FetchStockPriceResponse(ContinuityCode.NEXT, StockPriceBodyFixture.secondStockPriceBody());
    }

    public static FetchStockPriceResponse thirdFetchStockPriceResponse() {
        return new FetchStockPriceResponse(ContinuityCode.END, StockPriceBodyFixture.thirdStockPriceBody());
    }
}
