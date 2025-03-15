package com.gyeryongbrother.pickandtest.stockprice.dataaccess.data;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockPriceDataGenerator implements ApplicationRunner {

    private final StockPriceRepository stockPriceRepository;

    @Override
    public void run(ApplicationArguments args) {
        stockPrices(1L).forEach(stockPriceRepository::save);
        stockPrices(2L).forEach(stockPriceRepository::save);
    }

    private List<StockPrice> stockPrices(Long stockId) {
        return List.of(
                new StockPrice(null, stockId, LocalDate.of(2018, 12, 31), BigDecimal.valueOf(200)),
                new StockPrice(null, stockId, LocalDate.of(2019, 1, 1), BigDecimal.valueOf(200)),
                new StockPrice(null, stockId, LocalDate.of(2019, 1, 2), BigDecimal.valueOf(100)),
                new StockPrice(null, stockId, LocalDate.of(2019, 1, 3), BigDecimal.valueOf(300)),
                new StockPrice(null, stockId, LocalDate.of(2024, 1, 1), BigDecimal.valueOf(1000))
        );
    }
}
