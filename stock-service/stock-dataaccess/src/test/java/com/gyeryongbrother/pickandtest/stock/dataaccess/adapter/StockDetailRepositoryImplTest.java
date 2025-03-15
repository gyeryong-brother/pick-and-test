package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockDetailFixture.firstStockDetail;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockFixture.stock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.gyeryongbrother.pickandtest.stock.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockDetailRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("주식 상세 레포지토리를 구현한다")
class StockDetailRepositoryImplTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockDetailRepository stockDetailRepository;

    @Test
    @DisplayName("주식 상세를 저장한다")
    void save() {
        // given
        Stock stock = stock(null);
        Stock savedStock = stockRepository.save(stock);
        StockDetail stockDetail = firstStockDetail(savedStock.id());

        // when
        StockDetail result = stockDetailRepository.save(stockDetail);

        // then
        assertAll(
                () -> assertThat(result.id()).isPositive(),
                () -> assertThat(result).usingRecursiveComparison()
                        .ignoringExpectedNullFields()
                        .isEqualTo(stockDetail)
        );
    }
}
