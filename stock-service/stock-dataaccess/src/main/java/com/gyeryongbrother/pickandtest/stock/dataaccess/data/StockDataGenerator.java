package com.gyeryongbrother.pickandtest.stock.dataaccess.data;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividend;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.DividendRepository;
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
    private final DividendRepository dividendRepository;

    @Override
    public void run(ApplicationArguments args) {
        stockRepository.save(apple());
        stockRepository.save(tesla());
        dividends(1L).forEach(dividendRepository::save);
        dividends(2L).forEach(dividendRepository::save);
    }

    private Stock apple() {
        return Stock.builder()
                .name("Apple")
                .symbol("AAPL")
                .stockExchange(StockExchange.NASDAQ)
                .outstandingShares(10000L)
                .listingDate(LocalDate.of(2025, 1, 14))
                .build();
    }

    private Stock tesla() {
        return Stock.builder()
                .name("Tesla")
                .symbol("TSLA")
                .stockExchange(StockExchange.NASDAQ)
                .outstandingShares(5000L)
                .listingDate(LocalDate.of(2025, 1, 15))
                .build();
    }

    private List<Dividend> dividends(Long stockId) {
        return List.of(
                Dividend.builder()
                        .stockId(stockId)
                        .amount(BigDecimal.valueOf(0.1))
                        .date(LocalDate.of(2023, 3, 1))
                        .build(),
                Dividend.builder()
                        .stockId(stockId)
                        .amount(BigDecimal.valueOf(0.2))
                        .date(LocalDate.of(2023, 6, 1))
                        .build(),
                Dividend.builder()
                        .stockId(stockId)
                        .amount(BigDecimal.valueOf(0.3))
                        .date(LocalDate.of(2023, 9, 1))
                        .build(),
                Dividend.builder()
                        .stockId(stockId)
                        .amount(BigDecimal.valueOf(0.4))
                        .date(LocalDate.of(2023, 12, 1))
                        .build(),
                Dividend.builder()
                        .stockId(stockId)
                        .amount(BigDecimal.valueOf(0.5))
                        .date(LocalDate.of(2024, 3, 1))
                        .build()
        );
    }
}
