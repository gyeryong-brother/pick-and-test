package com.gyeryongbrother.pickandtest.domain.core;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class StockDetail {

    private final Stock stock;
    private final List<StockPrice> stockPrices;
    private final List<Dividend> dividends;
}
