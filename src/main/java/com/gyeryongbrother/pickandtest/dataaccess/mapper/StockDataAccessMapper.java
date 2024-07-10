package com.gyeryongbrother.pickandtest.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.dataaccess.entity.DividendEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockPriceEntity;
import com.gyeryongbrother.pickandtest.domain.Dividend;
import com.gyeryongbrother.pickandtest.domain.Stock;
import com.gyeryongbrother.pickandtest.domain.StockPrice;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StockDataAccessMapper {

    public Stock stockEntityToStock(StockEntity stockEntity) {
        List<StockPrice> stockPrices = stockEntity.getStockPrices().stream()
                .map(this::stockPriceEntityToStockPrice)
                .toList();
        List<Dividend> dividends = stockEntity.getDividends().stream()
                .map(this::dividendEntityToDividend)
                .toList();
        return Stock.builder()
                .id(stockEntity.getId())
                .name(stockEntity.getName())
                .symbol(stockEntity.getSymbol())
                .listingDate(stockEntity.getListingDate())
                .stockPrices(stockPrices)
                .dividends(dividends)
                .build();
    }

    private StockPrice stockPriceEntityToStockPrice(StockPriceEntity stockPriceEntity) {
        return StockPrice.builder()
                .id(stockPriceEntity.getId())
                .stockId(stockPriceEntity.getStock().getId())
                .date(stockPriceEntity.getDate())
                .price(stockPriceEntity.getPrice())
                .build();
    }

    private Dividend dividendEntityToDividend(DividendEntity dividendEntity) {
        return Dividend.builder()
                .id(dividendEntity.getId())
                .stockId(dividendEntity.getStock().getId())
                .date(dividendEntity.getDate())
                .amount(dividendEntity.getAmount())
                .build();
    }
}
