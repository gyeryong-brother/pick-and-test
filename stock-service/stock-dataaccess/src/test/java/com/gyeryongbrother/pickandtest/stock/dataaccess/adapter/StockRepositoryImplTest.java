package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.DividendFixture.dividends;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockFixture.stock;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockPriceFixture.stockPrices;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.gyeryongbrother.pickandtest.stock.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividend;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividends;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrices;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

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
                .stockPrices(StockPrices.from(stockPrices(null)))
                .dividends(Dividends.from(dividends(null)))
                .build();

        // when
        StockDetail result = stockRepository.save(stockDetail);
        Long stockId = result.getStock().getId();
        List<Long> stockPriceIds = result.getStockPrices().getValues().stream()
                .map(StockPrice::getId)
                .toList();
        List<Long> dividendIds = result.getDividends().getValues().stream()
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
