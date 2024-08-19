package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.dividend.dto;

import java.util.List;

public class DividendResponseFixture {

    public static DividendResponse appleDividendResponse() {
        return new DividendResponse(new DividendData(new Dividends(List.of(
                new DividendDetail("08/10/2023", "$0.24"),
                new DividendDetail("05/10/2023", "$0.24"),
                new DividendDetail("02/10/2023", "$0.23")
        ))));
    }

    public static DividendResponse microsoftDividendResponse() {
        return new DividendResponse(new DividendData(new Dividends(List.of(
                new DividendDetail("08/10/2023", "$0.68"),
                new DividendDetail("05/10/2023", "$0.68"),
                new DividendDetail("02/10/2023", "$0.68")
        ))));
    }

    public static DividendResponse nvidiaDividendResponse() {
        return new DividendResponse(new DividendData(new Dividends(List.of(
                new DividendDetail("09/10/2023", "$0.04"),
                new DividendDetail("06/10/2023", "$0.04"),
                new DividendDetail("03/10/2023", "$0.04")
        ))));
    }
}
