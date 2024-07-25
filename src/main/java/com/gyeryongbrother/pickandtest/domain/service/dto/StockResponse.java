package com.gyeryongbrother.pickandtest.domain.service.dto;

import com.gyeryongbrother.pickandtest.domain.core.Stock;

public record StockResponse(
        Long id,
        String name,
        String symbol
) {

    public static StockResponse from(Stock stock) {
        return new StockResponse(
                stock.getId(),
                stock.getName(),
                stock.getSymbol()
        );
    }
}
