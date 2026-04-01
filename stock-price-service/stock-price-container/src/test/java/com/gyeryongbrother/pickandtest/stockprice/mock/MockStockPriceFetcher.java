package com.gyeryongbrother.pickandtest.stockprice.mock;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockMinutePrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stocks;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceFetcher;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Primary
@Profile("performance-test")
public class MockStockPriceFetcher implements StockPriceFetcher {

    private static final long SIMULATED_LATENCY_MS = 5L;

    @Override
    public List<StockPrice> fetchStockPrices(Stocks stocks, LocalDate startDate) {
        try {
            Thread.sleep(15L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return stocks.symbols().stream()
                .map(symbol -> new StockPrice(
                        null,
                        stocks.stockIdBySymbol(symbol),
                        LocalDate.now(),
                        BigDecimal.valueOf(100.0)
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<StockMinutePrice> fetchStockMinutePrices(Stocks stocks, LocalDate startDate) {
        return Collections.emptyList();
    }
}
