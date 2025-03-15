package com.gyeryongbrother.pickandtest.stock.domain.service.dto;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;

public record StockResponse(
        Long id,
        String name,
        String symbol
) {

    public static StockResponse from(Stock stock) {
        return new StockResponse(
                stock.id(),
                stock.name(),
                stock.symbol()
        );
    }
}
