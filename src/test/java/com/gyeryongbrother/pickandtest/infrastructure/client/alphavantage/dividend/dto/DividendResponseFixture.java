package com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto;

import java.util.List;

public class DividendResponseFixture {

    public static DividendResponse empty() {
        return new DividendResponse("", List.of());
    }

    public static DividendResponse dividendResponse() {
        return new DividendResponse("AAPL", List.of(
                new DividendDetail("2023-08-10", "0.24"),
                new DividendDetail("2023-05-10", "0.24"),
                new DividendDetail("2023-02-10", "0.23")
        ));
    }
}
