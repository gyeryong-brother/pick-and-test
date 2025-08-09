package com.gyeryongbrother.pickandtest.stockprice.domain.core.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StockMinutePrice {

    private final Long id;
    private final Long stockId;
    private final LocalDateTime dateTime;
    private final BigDecimal price;

    public StockMinutePrice(Long id, Long stockId, LocalDateTime dateTime, BigDecimal price) {
        this.id = id;
        this.stockId = stockId;
        this.dateTime = dateTime;
        this.price = price;
    }

    public Long id() {
        return id;
    }

    public Long stockId() {
        return stockId;
    }

    public LocalDateTime dateTime() {
        return dateTime;
    }

    public BigDecimal price() {
        return price;
    }
}
