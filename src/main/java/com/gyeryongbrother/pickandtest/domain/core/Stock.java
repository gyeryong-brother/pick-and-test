package com.gyeryongbrother.pickandtest.domain.core;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@RequiredArgsConstructor
public class Stock {

    private final Long id;
    private final String name;
    private final String symbol;
    private final StockExchange stockExchange;
    private final LocalDate listingDate;
}
