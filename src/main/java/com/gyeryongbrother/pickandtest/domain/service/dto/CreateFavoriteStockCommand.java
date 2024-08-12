package com.gyeryongbrother.pickandtest.domain.service.dto;

import com.gyeryongbrother.pickandtest.domain.core.FavoriteStock;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;

public record CreateFavoriteStockCommand(
        Long memberId,
        Long stockId
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
