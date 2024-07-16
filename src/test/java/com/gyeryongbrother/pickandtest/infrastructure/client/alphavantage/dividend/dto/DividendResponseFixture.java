package com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto;

import java.util.List;

public class DividendResponseFixture {

    public static DividendResponse empty() {
        return new DividendResponse("", List.of());
    }
}
