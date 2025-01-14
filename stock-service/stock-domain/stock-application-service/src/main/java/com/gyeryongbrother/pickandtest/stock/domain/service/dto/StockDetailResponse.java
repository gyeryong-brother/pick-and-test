package com.gyeryongbrother.pickandtest.stock.domain.service.dto;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrice;
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

    public static StockDetailResponse from(StockDetail stockDetail) {
        Stock stock = stockDetail.getStock();
        StockPrice lastValue = stockDetail.getStockPrices().getLastValue();
        return new StockDetailResponse(
                stock.getId(),
                stock.getName(),
                stock.getSymbol(),
                stock.getStockExchange(),
                stock.getOutstandingShares(),
                stock.getListingDate(),
                stock.calculateMarketCapitalization(lastValue).getValue()
        );
    }
}
