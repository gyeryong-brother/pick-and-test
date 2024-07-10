package com.gyeryongbrother.pickandtest.domain;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class Stock {

    private final Long id;
    private final String name;
    private final String symbol;
    private final LocalDate listingDate;
    private final List<StockPrice> stockPrices;
    private final List<Dividend> dividends;
}
