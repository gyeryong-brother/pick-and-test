package com.gyeryongbrother.pickandtest.domain.core;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@RequiredArgsConstructor
public class StockPrice {

    private final Long id;
    private final Long stockId;
    private final LocalDate date;
    private final BigDecimal price;
}
