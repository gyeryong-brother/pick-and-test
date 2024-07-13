package com.gyeryongbrother.pickandtest.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.dataaccess.entity.DividendEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockPriceEntity;
import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import java.util.List;

import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockQueryRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockDataAccessMapper {

    public Stock stockEntityToStock(StockEntity stockEntity) {
        return Stock.builder()
                .id(stockEntity.getId())
                .name(stockEntity.getName())
                .symbol(stockEntity.getSymbol())
                .listingDate(stockEntity.getListingDate())
                .stockPrices(stockPriceEntitiesToStockPrices(stockEntity.getStockPrices()))
                .dividends(dividendEntitiesToDividends(stockEntity.getDividends()))
                .build();
    }

    private List<StockPrice> stockPriceEntitiesToStockPrices(List<StockPriceEntity> stockPriceEntities) {
        if (stockPriceEntities == null) {
            return List.of();
        }
        return stockPriceEntities.stream()
                .map(this::stockPriceEntityToStockPrice)
                .toList();
    }

    private StockPrice stockPriceEntityToStockPrice(StockPriceEntity stockPriceEntity) {
        return StockPrice.builder()
                .id(stockPriceEntity.getId())
                .stockId(stockPriceEntity.getStock().getId())
                .date(stockPriceEntity.getDate())
                .price(stockPriceEntity.getPrice())
                .build();
    }

    private List<Dividend> dividendEntitiesToDividends(List<DividendEntity> dividendEntities) {
        if (dividendEntities == null) {
            return List.of();
        }
        return dividendEntities.stream()
                .map(this::dividendEntityToDividend)
                .toList();
    }

    public Dividend dividendEntityToDividend(DividendEntity dividendEntity) {
        return Dividend.builder()
                .id(dividendEntity.getId())
                .stockId(dividendEntity.getStock().getId())
                .date(dividendEntity.getDate())
                .amount(dividendEntity.getAmount())
                .build();
    }

    public StockEntity stockToStockEntity(Stock stock) {
        return StockEntity.builder()
                .id(stock.getId())
                .name(stock.getName())
                .symbol(stock.getSymbol())
                .listingDate(stock.getListingDate())
                .build();
    }

    public DividendEntity dividendToDividendEntity(Dividend dividend) {
        StockEntity stockEntity=StockEntity.builder()
                .id(dividend.getStockId())
                .build();

        return DividendEntity.builder()
                .id(dividend.getId())
                .date(dividend.getDate())
                .amount(dividend.getAmount())
                .stock(stockEntity)
                .build();
    }
}
