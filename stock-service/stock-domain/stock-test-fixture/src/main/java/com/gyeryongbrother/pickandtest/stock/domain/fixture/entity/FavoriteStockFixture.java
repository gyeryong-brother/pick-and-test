package com.gyeryongbrother.pickandtest.stock.domain.fixture.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockFixture.stock;
import static lombok.AccessLevel.PRIVATE;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class FavoriteStockFixture {

    public static FavoriteStock favoriteStock(Long id) {
        return FavoriteStock.builder()
                .id(id)
                .memberId(1L)
                .stock(stock(1L))
                .build();
    }

    public static FavoriteStock favoriteStock(Stock stock) {
        return FavoriteStock.builder()
                .memberId(1L)
                .stock(stock)
                .build();
    }

    public static List<FavoriteStock> favoriteStocks(Long... stockIds) {
        return Arrays.stream(stockIds)
                .map(StockFixture::stock)
                .map(FavoriteStockFixture::favoriteStock)
                .toList();
    }

    public static List<FavoriteStock> favoriteStocks() {
        StockDetail firstStockDetail = StockDetail.builder()
                .lastStockPrice(BigDecimal.valueOf(1000))
                .compoundAnnualGrowthRate(BigDecimal.valueOf(58.49))
                .dividendYield(BigDecimal.valueOf(0.2))
                .build();
        StockDetail secondStockDetail = StockDetail.builder()
                .lastStockPrice(BigDecimal.valueOf(100))
                .compoundAnnualGrowthRate(BigDecimal.valueOf(0))
                .dividendYield(BigDecimal.valueOf(0.5))
                .build();
        return List.of(
                favoriteStock(stock(null, firstStockDetail)),
                favoriteStock(stock(null, secondStockDetail))
        );
    }
}
