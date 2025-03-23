package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto;

import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.ContinuityCode.END;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.ContinuityCode.NEXT;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBodyFixture.appleFirstStockPriceBody;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBodyFixture.appleSecondStockPriceBody;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBodyFixture.appleThirdStockPriceBody;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBodyFixture.microsoftFirstStockPriceBody;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBodyFixture.microsoftSecondStockPriceBody;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBodyFixture.microsoftThirdStockPriceBody;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBodyFixture.nvidiaFirstStockPriceBody;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBodyFixture.nvidiaSecondStockPriceBody;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBodyFixture.nvidiaThirdStockPriceBody;

public class StockPriceResponseFixture {

    public static StockPriceResponse appleFirstStockPriceResponse() {
        return new StockPriceResponse(NEXT, appleFirstStockPriceBody());
    }

    public static StockPriceResponse appleSecondStockPriceResponse() {
        return new StockPriceResponse(NEXT, appleSecondStockPriceBody());
    }

    public static StockPriceResponse appleThirdStockPriceResponse() {
        return new StockPriceResponse(END, appleThirdStockPriceBody());
    }

    public static StockPriceResponse microsoftFirstStockPriceResponse() {
        return new StockPriceResponse(NEXT, microsoftFirstStockPriceBody());
    }

    public static StockPriceResponse microsoftSecondStockPriceResponse() {
        return new StockPriceResponse(NEXT, microsoftSecondStockPriceBody());
    }

    public static StockPriceResponse microsoftThirdStockPriceResponse() {
        return new StockPriceResponse(END, microsoftThirdStockPriceBody());
    }

    public static StockPriceResponse nvidiaFirstStockPriceResponse() {
        return new StockPriceResponse(NEXT, nvidiaFirstStockPriceBody());
    }

    public static StockPriceResponse nvidiaSecondStockPriceResponse() {
        return new StockPriceResponse(NEXT, nvidiaSecondStockPriceBody());
    }

    public static StockPriceResponse nvidiaThirdStockPriceResponse() {
        return new StockPriceResponse(END, nvidiaThirdStockPriceBody());
    }
}
