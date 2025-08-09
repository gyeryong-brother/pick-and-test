package com.gyeryongbrother.pickandtest.stockprice.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockMinutePrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stocks;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceFetcher;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.DataServiceClient;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockPriceFetcherImpl implements StockPriceFetcher {

    private final DataServiceClient dataServiceClient;

    @Override
    public List<StockPrice> fetchStockPrices(Stocks stocks, LocalDate startDate) {
        try {
            return fetchStockPricesByStocks(stocks, startDate);
        } catch (StockPriceInfrastructureException e) {
            log.error("fetch failed. message: {}", e.getMessage());
            return List.of();
        }
    }

    private List<StockPrice> fetchStockPricesByStocks(Stocks stocks, LocalDate startDate) {
        Map<String, List<StockPriceResponse>> stockPricesBySymbol = dataServiceClient.fetchStockPrices(stocks.symbols(),
                startDate);
        return stockPricesBySymbol.entrySet().stream()
                .flatMap(it -> toStockPrices(it, stocks))
                .toList();
    }

    private Stream<StockPrice> toStockPrices(
            Entry<String, List<StockPriceResponse>> entry,
            Stocks stocks
    ) {
        Long stockId = stocks.stockIdBySymbol(entry.getKey());
        return entry.getValue().stream()
                .map(it -> it.toStockPrice(stockId));
    }

    @Override
    public List<StockMinutePrice> fetchStockMinutePrices(Stocks stocks, LocalDate startDate) {
        try {
            return fetchStockMinutePricesByStocks(stocks, startDate);
        } catch (StockPriceInfrastructureException e) {
            log.error("fetch failed. message: {}", e.getMessage());
            return List.of();
        }
    }

    private List<StockMinutePrice> fetchStockMinutePricesByStocks(Stocks stocks, LocalDate startDate) {
        Map<String, List<StockPriceResponse>> stockPricesBySymbol = dataServiceClient.fetchStockMinutePrices(
                stocks.symbols(),
                startDate);
        return stockPricesBySymbol.entrySet().stream()
                .flatMap(it -> toStockMinutePrices(it, stocks))
                .toList();
    }

    private Stream<StockMinutePrice> toStockMinutePrices(
            Entry<String, List<StockPriceResponse>> entry,
            Stocks stocks
    ) {
        Long stockId = stocks.stockIdBySymbol(entry.getKey());
        return entry.getValue().stream()
                .map(it -> it.toStockMinutePrice(stockId));
    }
}
