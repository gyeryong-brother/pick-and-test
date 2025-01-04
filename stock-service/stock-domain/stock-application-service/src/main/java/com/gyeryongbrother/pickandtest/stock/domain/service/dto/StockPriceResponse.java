package com.gyeryongbrother.pickandtest.stock.domain.service.dto;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrice;
import java.math.BigDecimal;
import java.time.LocalDate;

public record StockPriceResponse(
        LocalDate date,
        BigDecimal price
) {

    public static StockPriceResponse from(StockPrice stockPrice) {
        return new StockPriceResponse(stockPrice.getDate(), stockPrice.getPrice());
    }
}
