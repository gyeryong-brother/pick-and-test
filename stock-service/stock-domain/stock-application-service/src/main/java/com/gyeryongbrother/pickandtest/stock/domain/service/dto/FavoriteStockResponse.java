package com.gyeryongbrother.pickandtest.stock.domain.service.dto;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import java.math.BigDecimal;

public record FavoriteStockResponse(
        Long id,
        Long stockId,
        String name,
        String symbol,
        BigDecimal price,
        BigDecimal compoundAnnualGrowthRate,
        BigDecimal dividendYield
) {

    public static FavoriteStockResponse from(FavoriteStock favoriteStock) {
        Stock stock = favoriteStock.stock();
        StockDetail stockDetail = stock.stockInformation();
        return new FavoriteStockResponse(
                favoriteStock.id(),
                stock.id(),
                stock.name(),
                stock.symbol(),
                stockDetail.lastStockPrice(),
                stockDetail.compoundAnnualGrowthRate(),
                stockDetail.dividendYield()
        );
    }
}
