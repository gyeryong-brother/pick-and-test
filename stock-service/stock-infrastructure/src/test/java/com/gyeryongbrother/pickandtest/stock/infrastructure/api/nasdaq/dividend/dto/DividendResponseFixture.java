package com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.dividend.dto;

import java.util.List;

public class DividendResponseFixture {

    public static DividendResponse appleDividendResponse() {
        return new DividendResponse(new DividendData(new Dividends(List.of(
                new DividendDetail("08/10/2023", "$0.24"),
                new DividendDetail("05/10/2023", "$0.24"),
                new DividendDetail("02/10/2023", "$0.23")
        ))));
    }
}
