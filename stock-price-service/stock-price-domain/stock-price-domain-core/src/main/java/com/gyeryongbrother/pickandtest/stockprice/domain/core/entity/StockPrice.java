package com.gyeryongbrother.pickandtest.stockprice.domain.core.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StockPrice {

    private final Long id;
    private final Long stockId;
    private final LocalDate date;
    private final BigDecimal price;

    public StockPrice(
            Long id,
            Long stockId,
            LocalDate date,
            BigDecimal price
    ) {
        this.id = id;
        this.stockId = stockId;
        this.date = date;
        this.price = price;
    }

    public Long id() {
        return id;
    }

    public Long stockId() {
        return stockId;
    }

    public LocalDate date() {
        return date;
    }

    public BigDecimal price() {
        return price;
    }
}
