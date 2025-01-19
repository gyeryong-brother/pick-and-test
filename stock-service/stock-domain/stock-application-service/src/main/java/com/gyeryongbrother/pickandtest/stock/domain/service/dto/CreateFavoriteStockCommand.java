package com.gyeryongbrother.pickandtest.stock.domain.service.dto;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;

public record CreateFavoriteStockCommand(
        Long stockId,
        Long memberId
) {

    public FavoriteStock toDomain() {
        return FavoriteStock.builder()
                .memberId(memberId)
                .stockDetail(stockDetail())
                .build();
    }

    private StockDetail stockDetail() {
        Stock stock = Stock.builder()
                .id(stockId)
                .build();
        return StockDetail.builder()
                .stock(stock)
                .build();
    }
}
