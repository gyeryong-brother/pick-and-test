package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static com.gyeryongbrother.pickandtest.domain.core.DividendFixture.dividends;
import static com.gyeryongbrother.pickandtest.domain.core.StockFixture.stock;
import static com.gyeryongbrother.pickandtest.domain.core.StockPriceFixture.stockPrices;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("주식 레포지토리를 구현한다")
class StockRepositoryImplTest {

    @Autowired
    private StockRepository stockRepository;

    @Test
    @DisplayName("주식을 저장한다")
    void save() {
        // given
        Stock stock = stock("name", "symbol");

        // when
        Stock result = stockRepository.save(stock);

        // then
        assertThat(result.getId()).isPositive();
    }

    @Test
    @DisplayName("주식을 저장할 때 주가와 배당을 함께 저장한다")
    void saveStockDetail() {
        // given
        StockDetail stockDetail = StockDetail.builder()
                .stock(stock(null))
                .stockPrices(stockPrices(null))
                .dividends(dividends(null))
                .build();

        // when
        StockDetail result = stockRepository.save(stockDetail);
        Long stockId = result.getStock().getId();
        List<Long> stockPriceIds = result.getStockPrices().stream()
                .map(StockPrice::getId)
                .toList();
        List<Long> dividendIds = result.getDividends().stream()
                .map(Dividend::getId)
                .toList();

        // then
        assertAll(
                () -> assertThat(stockId).isPositive(),
                () -> assertThat(stockPriceIds).doesNotContainNull(),
                () -> assertThat(dividendIds).doesNotContainNull()
        );
    }
}
