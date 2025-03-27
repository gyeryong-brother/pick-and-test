package com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockPriceDate {

    public static final StockPriceDate EMPTY = new StockPriceDate(LocalDate.MIN);

    private final LocalDate value;

    public StockPriceDate tomorrow() {
        return new StockPriceDate(value.plusDays(1));
    }

    public LocalDate value() {
        return value;
    }
}
