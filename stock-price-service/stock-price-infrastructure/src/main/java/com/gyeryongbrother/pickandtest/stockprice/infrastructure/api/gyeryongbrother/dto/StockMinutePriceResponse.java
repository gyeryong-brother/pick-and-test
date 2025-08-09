package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.dto;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockMinutePrice;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public record StockMinutePriceResponse(
        String date,
        String price
) {

    public StockMinutePrice toDomain(Long stockId) {
        return new StockMinutePrice(null, stockId, toDateTime(), toPrice());
    }

    private LocalDateTime toDateTime() {
        return OffsetDateTime.parse(date)
                .toLocalDateTime();
    }

    private BigDecimal toPrice() {
        return new BigDecimal(price);
    }
}
