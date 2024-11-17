package com.gyeryongbrother.pickandtest.stock.domain.service.dto;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
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
