package com.gyeryongbrother.pickandtest.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.core.Dividends;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.domain.core.StockPrices;
import com.gyeryongbrother.pickandtest.domain.core.StockWithPrices;
import java.util.List;
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
                .stockExchange(stock.getStockExchange())
                .outstandingShares(stock.getOutstandingShares())
                .listingDate(stock.getListingDate())
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
                .build();
    }

    public StockWithPrices stockEntityToStockWithPrices(StockEntity stockEntity) {
        return StockWithPrices.builder()
                .stock(stockEntityToStock(stockEntity))
                .stockPrices(stockPriceDataAccessMapper.stockPriceEntitiesToStockPrices(stockEntity.getStockPrices()))
                .build();
    }

    public StockEntity stockDetailToStockEntity(StockDetail stockDetail) {
        StockEntity stockEntity = stockToStockEntity(stockDetail.getStock());
        stockDetail.getStockPrices().getValues().stream()
                .map(stockPriceDataAccessMapper::stockPriceToStockPriceEntity)
                .forEach(stockEntity::addStockPrice);
        stockDetail.getDividends().getValues().stream()
                .map(dividendDataAccessMapper::dividendToDividendEntity)
                .forEach(stockEntity::addDividend);
        return stockEntity;
    }

    public StockDetail stockEntityToStockDetail(StockEntity stockEntity) {
        List<Dividend> dividends = dividendDataAccessMapper.dividendEntitiesToDividends(stockEntity.getDividends());
        List<StockPrice> stockPrices =
                stockPriceDataAccessMapper.stockPriceEntitiesToStockPrices(stockEntity.getStockPrices());
        return StockDetail.builder()
                .stock(stockEntityToStock(stockEntity))
                .stockPrices(StockPrices.from(stockPrices))
                .dividends(Dividends.from(dividends))
                .build();
    }
}
