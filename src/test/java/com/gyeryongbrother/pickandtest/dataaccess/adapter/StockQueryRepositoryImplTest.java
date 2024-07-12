package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.DividendEntityFixture.dividendEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntityFixture.stockEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockPriceEntityFixture.stockPriceEntity;
import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.dataaccess.entity.DividendEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockPriceEntity;
import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockQueryRepository;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
class StockQueryRepositoryImplTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private StockQueryRepository stockQueryRepository;

    @Test
    void findById() {
        // given
        LocalDate januaryFirst = LocalDate.of(2024, 1, 1);
        LocalDate januarySecond = LocalDate.of(2024, 1, 2);
        LocalDate januaryThird = LocalDate.of(2024, 1, 3);
        BigDecimal oneHundred = BigDecimal.valueOf(100);
        BigDecimal twoHundred = BigDecimal.valueOf(200);
        BigDecimal threeHundred = BigDecimal.valueOf(200);

        StockEntity stockEntity = stockEntity("name", "symbol", januaryFirst);
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
        Stock stock = stockQueryRepository.findById(stockEntity.getId());

        // then
        assertThat(stock).usingRecursiveComparison()
                .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
