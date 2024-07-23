package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.DividendEntityFixture.dividendEntities;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntityFixture.stockEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockPriceEntityFixture.stockPriceEntities;
import static com.gyeryongbrother.pickandtest.domain.core.DividendFixture.dividends;
import static com.gyeryongbrother.pickandtest.domain.core.StockDetailFixture.stockDetail;
import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.domain.core.StockPriceFixture.stockPrices;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

import com.gyeryongbrother.pickandtest.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.repository.DividendJpaRepository;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockPriceJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockQueryRepository;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    private EntityManager entityManager;

    @Autowired
    private StockJpaRepository stockJpaRepository;

    @Autowired
    private StockPriceJpaRepository stockPriceJpaRepository;

    @Autowired
    private DividendJpaRepository dividendJpaRepository;

    @Autowired
    private StockQueryRepository stockQueryRepository;

    @Test
    @DisplayName("주가와 배당이 없을 때 아이디로 주식을 조회한다")
    void findByIdWithNoPricesAndNoDividends() {
        // given
        StockEntity stockEntity = stockJpaRepository.save(stockEntity());
        StockDetail expected = stockDetail(stockEntity.getId(), List.of(), List.of());

        // when
        StockDetail result = stockQueryRepository.findById(stockEntity.getId());

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("주가가 없을 때 아이디로 주식을 조회한다")
    void findByIdWithNoPrices() {
        // given
        StockEntity stockEntity = stockJpaRepository.save(stockEntity());
        dividendJpaRepository.saveAll(dividendEntities(stockEntity));
        Long stockId = stockEntity.getId();
        StockDetail expected = stockDetail(stockId, List.of(), dividends(stockId));
        entityManager.clear();

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
        StockEntity stockEntity = stockJpaRepository.save(stockEntity());
        stockPriceJpaRepository.saveAll(stockPriceEntities(stockEntity));
        dividendJpaRepository.saveAll(dividendEntities(stockEntity));
        Long stockId = stockEntity.getId();
        StockDetail expected = stockDetail(stockId, stockPrices(stockId), dividends(stockId));
        entityManager.clear();

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
        LocalDate januaryFirst = LocalDate.of(2024, 1, 1);
        StockEntity keyword = stockEntity("Keyword", "symbol", NASDAQ, januaryFirst);
        StockEntity keyboard = stockEntity("name", "keyboard", NASDAQ, januaryFirst);
        StockEntity keyPoint = stockEntity("KEY POINT", "symbol", NASDAQ, januaryFirst);
        stockJpaRepository.saveAll(List.of(
                keyword,
                stockEntity("name", "ke y pad", NASDAQ, januaryFirst),
                keyboard,
                stockEntity("a key player", "symbol", NASDAQ, januaryFirst),
                keyPoint
        ));
        List<Stock> expected = List.of(
                stock(keyword),
                stock(keyboard),
                stock(keyPoint)
        );

        // when
        List<Stock> result = stockQueryRepository.findAllByNameOrSymbol("key");

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }

    private Stock stock(StockEntity stockEntity) {
        return Stock.builder()
                .id(stockEntity.getId())
                .name(stockEntity.getName())
                .symbol(stockEntity.getSymbol())
                .stockExchange(stockEntity.getStockExchange())
                .listingDate(stockEntity.getListingDate())
                .build();
    }
}
