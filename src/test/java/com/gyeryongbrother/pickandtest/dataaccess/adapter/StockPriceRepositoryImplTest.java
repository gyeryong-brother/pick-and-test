package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockPriceRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("주가 레포지토리를 구현한다")
class StockPriceRepositoryImplTest {

    @Autowired
    private StockPriceRepository stockPriceRepository;

    @Autowired
    private StockRepository stockRepository;

    @Test
    @DisplayName("주가를 저장한다")
    void save() {
        //given
        LocalDate januaryFirst = LocalDate.of(2024, 1, 1);
        Stock stock = Stock.builder()
                .name("name")
                .symbol("symbol")
                .listingDate(januaryFirst)
                .build();
        Stock savedstock = stockRepository.save(stock);
        Long stockId = savedstock.getId();
        BigDecimal price = BigDecimal.valueOf(100);
        StockPrice stockPrice = StockPrice.builder()
                .stockId(stockId)
                .date(januaryFirst)
                .price(price)
                .build();
        StockPrice expected = StockPrice.builder()
                .stockId(stockId)
                .date(januaryFirst)
                .price(price)
                .build();

        //when
        StockPrice result = stockPriceRepository.save(stockPrice);

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
