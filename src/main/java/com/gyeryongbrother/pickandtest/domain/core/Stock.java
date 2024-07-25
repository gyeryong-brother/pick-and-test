package com.gyeryongbrother.pickandtest.domain.core;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Stock {

    private final Long id;
    private final String name;
    private final String symbol;
    private final StockExchange stockExchange;
    private final Long outstandingShares;
    private final LocalDate listingDate;
}
