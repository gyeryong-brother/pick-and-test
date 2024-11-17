package com.gyeryongbrother.pickandtest.stock.domain.fixture.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockFixture.stock;
import static lombok.AccessLevel.PRIVATE;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockWithPrices;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class StockWithPricesFixture {

    public static StockWithPrices stockWithPrices(Long stockId, List<StockPrice> stockPrices) {
        return StockWithPrices.builder()
                .stock(stock(stockId))
                .stockPrices(stockPrices)
                .build();
    }
}
