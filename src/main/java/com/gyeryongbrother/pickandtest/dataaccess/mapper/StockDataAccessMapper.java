package com.gyeryongbrother.pickandtest.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockDataAccessMapper {

    private final StockPriceDataAccessMapper stockPriceDataAccessMapper;
    private final DividendDataAccessMapper dividendDataAccessMapper;

    public StockEntity stockToStockEntity(Stock stock) {
        return StockEntity.builder()
                .id(stock.getId())
                .name(stock.getName())
                .symbol(stock.getSymbol())
                .listingDate(stock.getListingDate())
                .build();
    }

    public Stock stockEntityToStock(StockEntity stockEntity) {
        return Stock.builder()
                .id(stockEntity.getId())
                .name(stockEntity.getName())
                .symbol(stockEntity.getSymbol())
                .listingDate(stockEntity.getListingDate())
                .stockPrices(stockPriceDataAccessMapper.stockPriceEntitiesToStockPrices(stockEntity.getStockPrices()))
                .dividends(dividendDataAccessMapper.dividendEntitiesToDividends(stockEntity.getDividends()))
                .build();
    }
}
