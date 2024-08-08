package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.DividendEntityFixture.dividendEntities;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntityFixture.stockEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockPriceEntityFixture.stockPriceEntities;
import static com.gyeryongbrother.pickandtest.domain.core.DividendFixture.dividends;
import static com.gyeryongbrother.pickandtest.domain.core.StockDetailFixture.stockDetail;
import static com.gyeryongbrother.pickandtest.domain.core.StockFixture.stock;
import static com.gyeryongbrother.pickandtest.domain.core.StockPriceFixture.stockPrices;
import static com.gyeryongbrother.pickandtest.domain.core.StockWithPricesFixture.stockWithPrices;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

import com.gyeryongbrother.pickandtest.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import com.gyeryongbrother.pickandtest.domain.core.StockWithPrices;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockQueryRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("주식 조회 레포지토리를 구현한다")
class StockQueryRepositoryImplTest {

    @Autowired
    private StockJpaRepository stockJpaRepository;

    @Autowired
    private StockQueryRepository stockQueryRepository;

    @Test
    @DisplayName("주가가 없을 때 아이디로 주식과 주가를 조회한다")
    void findStockWithPricesByIdWithNoPrices() {
        // given
        StockEntity stockEntity = stockJpaRepository.save(stockEntity());
        Long stockId = stockEntity.getId();
        StockWithPrices expected = stockWithPrices(stockId, List.of());

        // when
        StockWithPrices result = stockQueryRepository.findStockWithPricesById(stockId);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("아이디로 주식과 주가를 조회한다")
    void findStockWithPricesById() {
        // given
        StockEntity stockEntity = stockEntity();
        stockPriceEntities(null).forEach(stockEntity::addStockPrice);
        stockJpaRepository.save(stockEntity);
        Long stockId = stockEntity.getId();
        StockWithPrices expected = stockWithPrices(stockId, stockPrices(stockId));

        // when
        StockWithPrices result = stockQueryRepository.findStockWithPricesById(stockId);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("주가와 배당이 없을 때 아이디로 주식을 조회한다")
    void findByIdWithNoPricesAndNoDividends() {
        // given
        StockEntity stockEntity = stockJpaRepository.save(stockEntity());
        Long stockId = stockEntity.getId();
        StockDetail expected = stockDetail(stockId, List.of(), List.of());

        // when
        StockDetail result = stockQueryRepository.findById(stockId);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("주가가 없을 때 아이디로 주식을 조회한다")
    void findByIdWithNoPrices() {
        // given
        StockEntity stockEntity = stockEntity();
        dividendEntities(null).forEach(stockEntity::addDividend);
        stockJpaRepository.save(stockEntity);
        Long stockId = stockEntity.getId();
        StockDetail expected = stockDetail(stockId, List.of(), dividends(stockId));

        // when
        StockDetail result = stockQueryRepository.findById(stockId);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("배당이 없을 때 아이디로 주식을 조회한다")
    void findByIdWithNoDividends() {
        // given
        StockEntity stockEntity = stockEntity();
        stockPriceEntities(null).forEach(stockEntity::addStockPrice);
        stockJpaRepository.save(stockEntity);
        Long stockId = stockEntity.getId();
        StockDetail expected = stockDetail(stockId, stockPrices(stockId), List.of());

        // when
        StockDetail result = stockQueryRepository.findById(stockId);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("아이디로 주식을 조회한다")
    void findById() {
        // given
        StockEntity stockEntity = stockEntity();
        stockPriceEntities(null).forEach(stockEntity::addStockPrice);
        dividendEntities(null).forEach(stockEntity::addDividend);
        stockJpaRepository.save(stockEntity);
        Long stockId = stockEntity.getId();
        StockDetail expected = stockDetail(stockId, stockPrices(stockId), dividends(stockId));

        // when
        StockDetail result = stockQueryRepository.findById(stockId);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("이름이나 심볼로 주식들을 조회한다")
    void findAllByNameOrSymbol() {
        // given
        stockJpaRepository.saveAll(List.of(
                stockEntity("Keyword", "symbol"),
                stockEntity("name", "ke y pad"),
                stockEntity("name", "keyboard"),
                stockEntity("a key player", "symbol"),
                stockEntity("KEY POINT", "symbol")
        ));
        List<Stock> expected = List.of(
                stock("Keyword", "symbol"),
                stock("name", "keyboard"),
                stock("KEY POINT", "symbol")
        );

        // when
        List<Stock> result = stockQueryRepository.findAllByNameOrSymbol("key");

        // then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
