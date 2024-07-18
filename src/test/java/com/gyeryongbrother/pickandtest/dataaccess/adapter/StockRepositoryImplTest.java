package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;
import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
class StockRepositoryImplTest {

    @Autowired
    private StockRepository stockRepository;

    @Test
    void save() {
        // given
        LocalDate januaryFirst = LocalDate.of(2024, 1, 1);
        Stock stock = Stock.builder()
                .name("name")
                .symbol("symbol")
                .stockExchange(NASDAQ)
                .listingDate(januaryFirst)
                .build();
        Stock expected = Stock.builder()
                .name("name")
                .symbol("symbol")
                .stockExchange(NASDAQ)
                .listingDate(januaryFirst)
                .stockPrices(List.of())
                .dividends(List.of())
                .build();

        // when
        Stock result = stockRepository.save(stock);

        // then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
