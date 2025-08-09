package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockFixture.stock;
import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.stock.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockRepository;
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
        assertThat(result.id()).isPositive();
    }
}
