package com.gyeryongbrother.pickandtest.stockprice.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockMinutePrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceFetcher;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.DataServiceClient;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.StockServiceClient;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.dto.StockMinutePricesResponse;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.dto.StockPricesResponse;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockPriceFetcherImpl implements StockPriceFetcher {

    private final StockServiceClient stockServiceClient;
    private final DataServiceClient dataServiceClient;

    @Override
    public List<StockPrice> fetchStockPrices(Long stockId, LocalDate startDate) {
        try {
            return fetchStockPricesById(stockId, startDate);
        } catch (StockPriceInfrastructureException e) {
            log.error("fetch failed. message: {}", e.getMessage());
            return List.of();
        }
    }

    private List<StockPrice> fetchStockPricesById(Long stockId, LocalDate startDate) {
        log.info("fetch stock. stock id: {}", stockId);
        Stock stock = stockServiceClient.fetchStock(stockId);
        log.info("stock fetched. symbol: {}", stock.symbol());
        StockPricesResponse stockPricesResponse = dataServiceClient.fetchStockPrices(stock.symbol(), startDate);
        return stockPricesResponse.stockPrices().stream()
                .map(it -> it.toDomain(stock.id()))
                .toList();
    }

    @Override
    public List<StockMinutePrice> fetchStockMinutePrices(Long stockId, LocalDate startDate) {
        log.info("fetch stock. stock id: {}", stockId);
        Stock stock = stockServiceClient.fetchStock(stockId);
        log.info("stock fetched. symbol: {}", stock.symbol());
        StockMinutePricesResponse stockMinutePricesResponse =
                dataServiceClient.fetchStockMinutePrices(stock.symbol(), startDate);
        return stockMinutePricesResponse.stockPrices().stream()
                .map(it -> it.toDomain(stock.id()))
                .toList();
    }
}
