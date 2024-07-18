package com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto;

import java.util.List;

public class DividendResponseFixture {

    public static DividendResponse appleDividendResponse() {
        return new DividendResponse("AAPL", List.of(
                new DividendDetail("2023-08-10", "0.24"),
                new DividendDetail("2023-05-10", "0.24"),
                new DividendDetail("2023-02-10", "0.23")
        ));
    }

    public static DividendResponse microsoftDividendResponse() {
        return new DividendResponse("MSFT", List.of(
                new DividendDetail("2023-08-10", "0.68"),
                new DividendDetail("2023-05-10", "0.68"),
                new DividendDetail("2023-02-10", "0.68")
        ));
    }

    public static DividendResponse nvidiaDividendResponse() {
        return new DividendResponse("NVDA", List.of(
                new DividendDetail("2023-09-10", "0.04"),
                new DividendDetail("2023-06-10", "0.04"),
                new DividendDetail("2023-03-10", "0.04")
        ));
    }
}
