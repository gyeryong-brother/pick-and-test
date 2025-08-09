package com.gyeryongbrother.pickandtest.stock.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockDetailEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockDataAccessMapper {

    public StockEntity stockToStockEntity(Stock stock) {
        return StockEntity.builder()
                .id(stock.id())
                .name(stock.name())
                .symbol(stock.symbol())
                .stockExchange(stock.stockExchange())
                .outstandingShares(stock.outstandingShares())
                .listingDate(stock.listingDate())
                .build();
    }

    public Stock stockEntityToStock(StockEntity stockEntity) {
        return Stock.builder()
                .id(stockEntity.getId())
                .name(stockEntity.getName())
                .symbol(stockEntity.getSymbol())
                .stockExchange(stockEntity.getStockExchange())
                .outstandingShares(stockEntity.getOutstandingShares())
                .listingDate(stockEntity.getListingDate())
                .stockDetail(getStockInformation(stockEntity))
                .build();
    }

    private StockDetail getStockInformation(StockEntity stockEntity) {
        StockDetailEntity stockInformation = stockEntity.getStockDetail();
        if (stockInformation == null) {
            return null;
        }
        return stockInformation.toDomain();
    }
}
