package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockEntityFixture.stockEntity;
import static com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockPriceEntityFixture.stockPriceEntities;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockPriceFixture.stockPrices;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

import com.gyeryongbrother.pickandtest.stock.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.StockPriceJpaRepository;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockPriceQueryRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("주가 조회 레포지토리를 구현한다")
class StockPriceQueryRepositoryImplTest {

    @Autowired
    private StockJpaRepository stockJpaRepository;

    @Autowired
    private StockPriceJpaRepository stockPriceJpaRepository;

    @Autowired
    private StockPriceQueryRepository stockPriceQueryRepository;

    @Test
    @DisplayName("주식 아이디로 주가들을 날짜순으로 조회한다")
    void findAllByStockId() {
        // given
        StockEntity stockEntity = stockJpaRepository.save(stockEntity());
        stockPriceJpaRepository.saveAll(stockPriceEntities(stockEntity));
        List<StockPrice> expected = stockPrices(stockEntity.getId());

        // when
        List<StockPrice> result = stockPriceQueryRepository.findAllByStockId(stockEntity.getId());

        // then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }
}
