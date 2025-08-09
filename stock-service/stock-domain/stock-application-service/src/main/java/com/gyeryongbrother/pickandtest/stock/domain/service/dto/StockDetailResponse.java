package com.gyeryongbrother.pickandtest.stock.domain.service.dto;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import java.time.LocalDate;

public record StockDetailResponse(
        Long id,
        String name,
        String symbol,
        StockExchange stockExchange,
        Long outstandingShares,
        LocalDate listingDate,
        Long marketCapitalization
) {

    public static StockDetailResponse from(Stock stock) {
        return new StockDetailResponse(
                stock.id(),
                stock.name(),
                stock.symbol(),
                stock.stockExchange(),
                stock.outstandingShares(),
                stock.listingDate(),
                stock.calculateMarketCapitalization()
        );
    }
}
