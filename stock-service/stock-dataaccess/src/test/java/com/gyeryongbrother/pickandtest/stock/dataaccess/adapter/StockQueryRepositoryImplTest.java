package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockEntityFixture.stockEntity;
import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange.NYQ;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockFixture.stock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

import com.gyeryongbrother.pickandtest.stock.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockQueryRepository;
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
    @DisplayName("아이디로 주식을 조회한다")
    void getById() {
        // given
        StockEntity stockEntity = stockEntity();
        stockJpaRepository.save(stockEntity);
        Long stockId = stockEntity.getId();
        Stock expected = stock(stockId);

        // when
        Stock result = stockQueryRepository.getById(stockId);

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

    @Test
    @DisplayName("거래소로 심볼들을 조회한다")
    void findAllSymbolsByStockExchange() {
        // when
        List<String> result = stockQueryRepository.findAllSymbolsByStockExchange(NYQ);

        // then
        assertThat(result).isEmpty();
    }
}
