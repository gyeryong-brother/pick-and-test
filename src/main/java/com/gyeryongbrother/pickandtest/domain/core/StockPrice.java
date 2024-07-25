package com.gyeryongbrother.pickandtest.domain.core;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class StockPrice {

    private final Long id;
    private final Long stockId;
    private final LocalDate date;
    private final BigDecimal price;

    public MarketCapitalization calculateMarketCapitalization(Long outstandingShares) {
        BigDecimal marketValue = price.multiply(BigDecimal.valueOf(outstandingShares));
        return MarketCapitalization.builder()
                .date(date)
                .value(marketValue.longValue())
                .build();
    }
}
