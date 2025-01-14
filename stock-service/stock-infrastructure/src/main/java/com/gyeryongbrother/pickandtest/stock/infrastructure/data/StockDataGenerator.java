package com.gyeryongbrother.pickandtest.stock.infrastructure.data;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividend;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividends;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrices;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockDataGenerator implements ApplicationRunner {

    private final StockRepository stockRepository;

    @Override
    public void run(ApplicationArguments args) {
        stockRepository.save(apple());
        stockRepository.save(tesla());
    }

    private StockDetail apple() {
        Stock stock = Stock.builder()
                .name("Apple")
                .symbol("AAPL")
                .stockExchange(StockExchange.NASDAQ)
                .outstandingShares(10000L)
                .listingDate(LocalDate.of(2025, 1, 14))
                .build();
        return StockDetail.builder()
                .stock(stock)
                .stockPrices(stockPrices())
                .dividends(dividends())
                .build();
    }

    private StockDetail tesla() {
        Stock stock = Stock.builder()
                .name("Tesla")
                .symbol("TSLA")
                .stockExchange(StockExchange.NASDAQ)
                .outstandingShares(5000L)
                .listingDate(LocalDate.of(2025, 1, 15))
                .build();
        return StockDetail.builder()
                .stock(stock)
                .stockPrices(stockPrices())
                .dividends(dividends())
                .build();
    }

    private StockPrices stockPrices() {
        List<StockPrice> stockPrices = List.of(
                StockPrice.builder()
                        .price(BigDecimal.valueOf(200))
                        .date(LocalDate.of(2018, 12, 31))
                        .build(),
                StockPrice.builder()
                        .price(BigDecimal.valueOf(200))
                        .date(LocalDate.of(2019, 1, 1))
                        .build(),
                StockPrice.builder()
                        .price(BigDecimal.valueOf(100))
                        .date(LocalDate.of(2019, 1, 2))
                        .build(),
                StockPrice.builder()
                        .price(BigDecimal.valueOf(300))
                        .date(LocalDate.of(2019, 1, 3))
                        .build(),
                StockPrice.builder()
                        .price(BigDecimal.valueOf(1000))
                        .date(LocalDate.of(2024, 1, 1))
                        .build()
        );
        return StockPrices.from(stockPrices);
    }

    private Dividends dividends() {
        List<Dividend> dividends = List.of(
                Dividend.builder()
                        .amount(BigDecimal.valueOf(0.1))
                        .date(LocalDate.of(2023, 3, 1))
                        .build(),
                Dividend.builder()
                        .amount(BigDecimal.valueOf(0.2))
                        .date(LocalDate.of(2023, 6, 1))
                        .build(),
                Dividend.builder()
                        .amount(BigDecimal.valueOf(0.3))
                        .date(LocalDate.of(2023, 9, 1))
                        .build(),
                Dividend.builder()
                        .amount(BigDecimal.valueOf(0.4))
                        .date(LocalDate.of(2023, 12, 1))
                        .build(),
                Dividend.builder()
                        .amount(BigDecimal.valueOf(0.5))
                        .date(LocalDate.of(2024, 3, 1))
                        .build()
        );
        return Dividends.from(dividends);
    }
}
