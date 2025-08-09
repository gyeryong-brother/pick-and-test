package com.gyeryongbrother.pickandtest.stock.domain.core.entity;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import java.time.LocalDate;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class Stock {

    private final Long id;
    private final String name;
    private final String symbol;
    private final StockExchange stockExchange;
    private final Long outstandingShares;
    private final LocalDate listingDate;
    private final StockDetail stockDetail;

    public Long calculateMarketCapitalization() {
        if (stockDetail == null) {
            return null;
        }
        return stockDetail.calculateMarketCapitalization(outstandingShares);
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String symbol() {
        return symbol;
    }

    public StockExchange stockExchange() {
        return stockExchange;
    }

    public Long outstandingShares() {
        return outstandingShares;
    }

    public LocalDate listingDate() {
        return listingDate;
    }

    public StockDetail stockInformation() {
        return stockDetail;
    }
}
