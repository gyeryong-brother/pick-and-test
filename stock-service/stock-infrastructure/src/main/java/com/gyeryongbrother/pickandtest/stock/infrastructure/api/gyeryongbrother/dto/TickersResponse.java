package com.gyeryongbrother.pickandtest.stock.infrastructure.api.gyeryongbrother.dto;

import java.util.List;

public record TickersResponse(
        List<String> tickers
) {
}
