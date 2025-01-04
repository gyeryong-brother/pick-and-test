package com.gyeryongbrother.pickandtest.stock.domain.fixture.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.DividendFixture.dividends;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.DividendFixture.twoDividends;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockDetailFixture.stockDetail;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockFixture.stock;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockPriceFixture.stockPrice;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockPriceFixture.stockPrices;
import static lombok.AccessLevel.PRIVATE;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import java.util.Arrays;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class FavoriteStockFixture {

    public static FavoriteStock favoriteStock(Long id) {
        StockDetail stockDetail = StockDetail.builder()
                .stock(stock(1L))
                .build();
        return FavoriteStock.builder()
                .id(id)
                .memberId(1L)
                .stockDetail(stockDetail)
                .build();
    }

    public static FavoriteStock favoriteStock(Stock stock) {
        StockDetail stockDetail = StockDetail.builder()
                .stock(stock)
                .build();
        return FavoriteStock.builder()
                .memberId(1L)
                .stockDetail(stockDetail)
                .build();
    }

    public static List<FavoriteStock> favoriteStocks(Long... stockIds) {
        return Arrays.stream(stockIds)
                .map(StockDetailFixture::stockDetail)
                .map(FavoriteStockFixture::favoriteStock)
                .toList();
    }

    public static FavoriteStock favoriteStock(StockDetail stockDetail) {
        return FavoriteStock.builder()
                .memberId(1L)
                .stockDetail(stockDetail)
                .build();
    }

    public static List<FavoriteStock> favoriteStocks() {
        return List.of(
                favoriteStock(stockDetail(null, stockPrices(), dividends())),
                favoriteStock(stockDetail(null, List.of(stockPrice()), twoDividends()))
        );
    }
}
