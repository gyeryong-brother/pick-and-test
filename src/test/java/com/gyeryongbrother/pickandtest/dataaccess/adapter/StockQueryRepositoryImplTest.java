package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.DividendEntityFixture.dividendEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntityFixture.stockEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockPriceEntityFixture.stockPriceEntity;
import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

import com.gyeryongbrother.pickandtest.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.dataaccess.entity.DividendEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockPriceEntity;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;
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
    private StockQueryRepository stockQueryRepository;

    @Test
    @DisplayName("아이디로 주식을 조회한다")
    void findById() {
        // given
        LocalDate januaryFirst = LocalDate.of(2024, 1, 1);
        LocalDate januarySecond = LocalDate.of(2024, 1, 2);
        LocalDate januaryThird = LocalDate.of(2024, 1, 3);
        BigDecimal oneHundred = BigDecimal.valueOf(100);
        BigDecimal twoHundred = BigDecimal.valueOf(200);
        BigDecimal threeHundred = BigDecimal.valueOf(200);

        StockEntity stockEntity = stockEntity("name", "symbol", NASDAQ, januaryFirst);
        StockPriceEntity januaryFirstStockPriceEntity = stockPriceEntity(stockEntity, januaryFirst, oneHundred);
        StockPriceEntity januarySecondStockPriceEntity = stockPriceEntity(stockEntity, januarySecond, twoHundred);
        StockPriceEntity januaryThirdStockPriceEntity = stockPriceEntity(stockEntity, januaryThird, threeHundred);
        DividendEntity januaryFirstDividendEntity = dividendEntity(stockEntity, januaryFirst, oneHundred);
        DividendEntity januarySecondDividendEntity = dividendEntity(stockEntity, januarySecond, twoHundred);

        entityManager.persist(stockEntity);
        entityManager.persist(januaryFirstStockPriceEntity);
        entityManager.persist(januarySecondStockPriceEntity);
        entityManager.persist(januaryThirdStockPriceEntity);
        entityManager.persist(januaryFirstDividendEntity);
        entityManager.persist(januarySecondDividendEntity);

        entityManager.flush();
        entityManager.clear();

        StockPrice januaryFirstStockPrice = StockPrice.builder()
                .stockId(stockEntity.getId())
                .date(januaryFirst)
                .price(oneHundred)
                .build();
        StockPrice januarySecondStockPrice = StockPrice.builder()
                .stockId(stockEntity.getId())
                .date(januarySecond)
                .price(twoHundred)
                .build();
        StockPrice januaryThirdStockPrice = StockPrice.builder()
                .stockId(stockEntity.getId())
                .date(januaryThird)
                .price(threeHundred)
                .build();
        Dividend januaryFirstDividend = Dividend.builder()
                .stockId(stockEntity.getId())
                .date(januaryFirst)
                .amount(oneHundred)
                .build();
        Dividend januarySecondDividend = Dividend.builder()
                .stockId(stockEntity.getId())
                .date(januarySecond)
                .amount(twoHundred)
                .build();
        Stock expected = Stock.builder()
                .name("name")
                .symbol("symbol")
                .stockExchange(NASDAQ)
                .listingDate(januaryFirst)
                .stockPrices(List.of(januaryFirstStockPrice, januarySecondStockPrice, januaryThirdStockPrice))
                .dividends(List.of(januaryFirstDividend, januarySecondDividend))
                .build();

        // when
        Stock stock = stockQueryRepository.findById(stockEntity.getId());

        // then
        assertThat(stock).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    void findByName() {
        // given
        LocalDate januaryFirst = LocalDate.of(2024, 1, 1);
        LocalDate januarySecond = LocalDate.of(2024, 1, 2);
        LocalDate januaryThird = LocalDate.of(2024, 1, 3);
        BigDecimal oneHundred = BigDecimal.valueOf(100);
        BigDecimal twoHundred = BigDecimal.valueOf(200);
        BigDecimal threeHundred = BigDecimal.valueOf(200);

        StockEntity stockEntity = stockEntity("name", "symbol", NASDAQ, januaryFirst);
        StockPriceEntity januaryFirstStockPriceEntity = stockPriceEntity(stockEntity, januaryFirst, oneHundred);
        StockPriceEntity januarySecondStockPriceEntity = stockPriceEntity(stockEntity, januarySecond, twoHundred);
        StockPriceEntity januaryThirdStockPriceEntity = stockPriceEntity(stockEntity, januaryThird, threeHundred);
        DividendEntity januaryFirstDividendEntity = dividendEntity(stockEntity, januaryFirst, oneHundred);
        DividendEntity januarySecondDividendEntity = dividendEntity(stockEntity, januarySecond, twoHundred);

        entityManager.persist(stockEntity);
        entityManager.persist(januaryFirstStockPriceEntity);
        entityManager.persist(januarySecondStockPriceEntity);
        entityManager.persist(januaryThirdStockPriceEntity);
        entityManager.persist(januaryFirstDividendEntity);
        entityManager.persist(januarySecondDividendEntity);

        entityManager.flush();
        entityManager.clear();

        StockPrice januaryFirstStockPrice = StockPrice.builder()
                .stockId(stockEntity.getId())
                .date(januaryFirst)
                .price(oneHundred)
                .build();
        StockPrice januarySecondStockPrice = StockPrice.builder()
                .stockId(stockEntity.getId())
                .date(januarySecond)
                .price(twoHundred)
                .build();
        StockPrice januaryThirdStockPrice = StockPrice.builder()
                .stockId(stockEntity.getId())
                .date(januaryThird)
                .price(threeHundred)
                .build();
        Dividend januaryFirstDividend = Dividend.builder()
                .stockId(stockEntity.getId())
                .date(januaryFirst)
                .amount(oneHundred)
                .build();
        Dividend januarySecondDividend = Dividend.builder()
                .stockId(stockEntity.getId())
                .date(januarySecond)
                .amount(twoHundred)
                .build();
        Stock expected = Stock.builder()
                .name("name")
                .symbol("symbol")
                .listingDate(januaryFirst)
                .stockPrices(List.of(januaryFirstStockPrice, januarySecondStockPrice, januaryThirdStockPrice))
                .dividends(List.of(januaryFirstDividend, januarySecondDividend))
                .build();

        // when
        Stock stock = stockQueryRepository.findByName(stockEntity.getName());

        // then
        assertThat(stock).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    void findBySymbol() {
        // given
        LocalDate januaryFirst = LocalDate.of(2024, 1, 1);
        LocalDate januarySecond = LocalDate.of(2024, 1, 2);
        LocalDate januaryThird = LocalDate.of(2024, 1, 3);
        BigDecimal oneHundred = BigDecimal.valueOf(100);
        BigDecimal twoHundred = BigDecimal.valueOf(200);
        BigDecimal threeHundred = BigDecimal.valueOf(200);

        StockEntity stockEntity = stockEntity("name", "symbol", NASDAQ, januaryFirst);
        StockPriceEntity januaryFirstStockPriceEntity = stockPriceEntity(stockEntity, januaryFirst, oneHundred);
        StockPriceEntity januarySecondStockPriceEntity = stockPriceEntity(stockEntity, januarySecond, twoHundred);
        StockPriceEntity januaryThirdStockPriceEntity = stockPriceEntity(stockEntity, januaryThird, threeHundred);
        DividendEntity januaryFirstDividendEntity = dividendEntity(stockEntity, januaryFirst, oneHundred);
        DividendEntity januarySecondDividendEntity = dividendEntity(stockEntity, januarySecond, twoHundred);

        entityManager.persist(stockEntity);
        entityManager.persist(januaryFirstStockPriceEntity);
        entityManager.persist(januarySecondStockPriceEntity);
        entityManager.persist(januaryThirdStockPriceEntity);
        entityManager.persist(januaryFirstDividendEntity);
        entityManager.persist(januarySecondDividendEntity);

        entityManager.flush();
        entityManager.clear();

        StockPrice januaryFirstStockPrice = StockPrice.builder()
                .stockId(stockEntity.getId())
                .date(januaryFirst)
                .price(oneHundred)
                .build();
        StockPrice januarySecondStockPrice = StockPrice.builder()
                .stockId(stockEntity.getId())
                .date(januarySecond)
                .price(twoHundred)
                .build();
        StockPrice januaryThirdStockPrice = StockPrice.builder()
                .stockId(stockEntity.getId())
                .date(januaryThird)
                .price(threeHundred)
                .build();
        Dividend januaryFirstDividend = Dividend.builder()
                .stockId(stockEntity.getId())
                .date(januaryFirst)
                .amount(oneHundred)
                .build();
        Dividend januarySecondDividend = Dividend.builder()
                .stockId(stockEntity.getId())
                .date(januarySecond)
                .amount(twoHundred)
                .build();
        Stock expected = Stock.builder()
                .name("name")
                .symbol("symbol")
                .listingDate(januaryFirst)
                .stockPrices(List.of(januaryFirstStockPrice, januarySecondStockPrice, januaryThirdStockPrice))
                .dividends(List.of(januaryFirstDividend, januarySecondDividend))
                .build();

        // when
        Stock stock = stockQueryRepository.findBySymbol(stockEntity.getSymbol());

        // then
        assertThat(stock).usingRecursiveComparison()
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
        List<StockResponse> expected = List.of(
                stockResponse(keyword),
                stockResponse(keyboard),
                stockResponse(keyPoint)
        );

        // when
        List<StockResponse> result = stockQueryRepository.findAllByNameOrSymbol("key");

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }

    private StockResponse stockResponse(StockEntity stockEntity) {
        return new StockResponse(
                stockEntity.getId(),
                stockEntity.getName(),
                stockEntity.getSymbol()
        );
    }
}
