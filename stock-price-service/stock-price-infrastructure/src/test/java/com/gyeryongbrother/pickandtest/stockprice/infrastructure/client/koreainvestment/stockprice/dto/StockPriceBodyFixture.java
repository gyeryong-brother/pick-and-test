package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.dto.StockPriceBody;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.dto.StockPriceDetail;
import java.util.Arrays;
import java.util.List;

public class StockPriceBodyFixture {

    public static StockPriceBody stockPriceBody(String date) {
        return stockPriceBody(
                new StockPriceDetail(date, "230.5400")
        );
    }

    public static StockPriceBody stockPriceBody(String... dates) {
        List<StockPriceDetail> stockPriceDetails = Arrays.stream(dates)
                .map(it -> new StockPriceDetail(it, "230.5400"))
                .toList();
        return stockPriceBody(stockPriceDetails);
    }

    private static StockPriceBody stockPriceBody(List<StockPriceDetail> stockPriceDetails) {
        return new StockPriceBody(
                "0",
                "MCA00000",
                "정상처리 되었습니다.",
                stockPriceDetails
        );
    }

    public static StockPriceBody appleFirstStockPriceBody() {
        return stockPriceBody(
                new StockPriceDetail("20240712", "230.5400"),
                new StockPriceDetail("20240711", "227.5700"),
                new StockPriceDetail("20240710", "232.9800")
        );
    }

    private static StockPriceBody stockPriceBody(StockPriceDetail... stockPriceDetails) {
        return new StockPriceBody(
                "0",
                "MCA00000",
                "정상처리 되었습니다.",
                List.of(stockPriceDetails)
        );
    }

    public static StockPriceBody appleSecondStockPriceBody() {
        return stockPriceBody(
                new StockPriceDetail("20240709", "228.6800"),
                new StockPriceDetail("20240708", "227.8200"),
                new StockPriceDetail("20240705", "226.3400")
        );
    }

    public static StockPriceBody appleThirdStockPriceBody() {
        return stockPriceBody(
                new StockPriceDetail("20240703", "221.5500"),
                new StockPriceDetail("20240702", "220.2700")
        );
    }

    public static StockPriceBody microsoftFirstStockPriceBody() {
        return stockPriceBody(
                new StockPriceDetail("20240712", "453.5500"),
                new StockPriceDetail("20240711", "454.7000"),
                new StockPriceDetail("20240710", "466.2500")
        );
    }

    public static StockPriceBody microsoftSecondStockPriceBody() {
        return stockPriceBody(
                new StockPriceDetail("20240709", "459.5400"),
                new StockPriceDetail("20240708", "466.2400")
        );
    }

    public static StockPriceBody microsoftThirdStockPriceBody() {
        return stockPriceBody(
                new StockPriceDetail("20240705", "467.5600"),
                new StockPriceDetail("20240703", "460.7700"),
                new StockPriceDetail("20240702", "459.2800")
        );
    }

    public static StockPriceBody nvidiaFirstStockPriceBody() {
        return stockPriceBody(
                new StockPriceDetail("20240712", "129.2400"),
                new StockPriceDetail("20240711", "127.4000")
        );
    }

    public static StockPriceBody nvidiaSecondStockPriceBody() {
        return stockPriceBody(
                new StockPriceDetail("20240710", "134.9100"),
                new StockPriceDetail("20240709", "131.3800"),
                new StockPriceDetail("20240708", "128.2000")
        );
    }

    public static StockPriceBody nvidiaThirdStockPriceBody() {
        return stockPriceBody(
                new StockPriceDetail("20240705", "125.8300"),
                new StockPriceDetail("20240703", "128.2800"),
                new StockPriceDetail("20240702", "122.6700")
        );
    }
}
