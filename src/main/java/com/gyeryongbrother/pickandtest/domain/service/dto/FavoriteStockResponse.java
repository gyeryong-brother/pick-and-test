package com.gyeryongbrother.pickandtest.domain.service.dto;

import com.gyeryongbrother.pickandtest.domain.core.FavoriteStock;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import java.math.BigDecimal;

public record FavoriteStockResponse(
        Long id,
        String name,
        String symbol,
        BigDecimal price,
        BigDecimal compoundAnnualGrowthRate,
        BigDecimal dividendYield
) {

    public static FavoriteStockResponse from(FavoriteStock favoriteStock) {
        StockDetail stockDetail = favoriteStock.getStockDetail();
        Stock stock = stockDetail.getStock();
        return new FavoriteStockResponse(
                favoriteStock.getId(),
                stock.getName(),
                stock.getSymbol(),
                stockDetail.getLastStockPrice(),
                stockDetail.calculateCompoundAnnualGrowthRate(),
                stockDetail.calculateDividendYield()
        );
    }
}
