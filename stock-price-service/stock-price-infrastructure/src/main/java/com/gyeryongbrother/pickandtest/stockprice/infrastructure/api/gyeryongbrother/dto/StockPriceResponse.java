package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.dto;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockMinutePrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record StockPriceResponse(
        String date,
        String price
) {

    public StockPrice toStockPrice(Long stockId) {
        return new StockPrice(null, stockId, toDate(), toPrice());
    }

    private LocalDate toDate() {
        return LocalDateTime.parse(date)
                .toLocalDate();
    }

    private BigDecimal toPrice() {
        return new BigDecimal(price);
    }

    public StockMinutePrice toStockMinutePrice(Long stockId) {
        return new StockMinutePrice(null, stockId, toDateTime(), toPrice());
    }

    private LocalDateTime toDateTime() {
        return LocalDateTime.parse(date);
    }
}
