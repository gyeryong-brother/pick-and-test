package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static com.gyeryongbrother.pickandtest.domain.core.DividendFixture.dividends;
import static com.gyeryongbrother.pickandtest.domain.core.StockDetailFixture.stockDetail;
import static com.gyeryongbrother.pickandtest.domain.core.StockFixture.stock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

@SpringBootTest
@DisplayName("주식을 조회한다")
class StockQueryRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private DividendRepository dividendRepository;

    @Autowired
    private StockQueryRepository stockQueryRepository;

    @Test
    @DisplayName("주가가 없을 때 아이디로 주식을 조회한다")
    void findByIdWithNoPrices() {
        // given
        Stock stock = stockRepository.save(stock(null));
        Long stockId = stock.getId();
        dividends(stockId).forEach(dividendRepository::save);
        StockDetail expected = stockDetail(stockId, List.of(), dividends(stockId));

        // when
        StockDetail result = stockQueryRepository.findById(stockId);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
