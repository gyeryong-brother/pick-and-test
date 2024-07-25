package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntityFixture.stockEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.StockPriceEntityFixture.stockPriceEntity;
import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

import com.gyeryongbrother.pickandtest.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockPriceEntity;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockPriceJpaRepository;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockPriceQueryRepository;
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
        LocalDate januaryFirst = LocalDate.of(2024, 1, 1);
        LocalDate januarySecond = LocalDate.of(2024, 1, 2);
        LocalDate januaryThird = LocalDate.of(2024, 1, 3);
        BigDecimal oneHundred = BigDecimal.valueOf(100);
        BigDecimal twoHundred = BigDecimal.valueOf(200);
        BigDecimal threeHundred = BigDecimal.valueOf(300);

        StockEntity stockEntity = stockEntity("name", "symbol", NASDAQ, januaryFirst);
        stockJpaRepository.save(stockEntity);
        StockPriceEntity januaryFirstStockPriceEntity = stockPriceEntity(stockEntity, januaryFirst, oneHundred);
        StockPriceEntity januarySecondStockPriceEntity = stockPriceEntity(stockEntity, januarySecond, twoHundred);
        StockPriceEntity januaryThirdStockPriceEntity = stockPriceEntity(stockEntity, januaryThird, threeHundred);
        stockPriceJpaRepository.saveAll(List.of(
                januarySecondStockPriceEntity,
                januaryThirdStockPriceEntity,
                januaryFirstStockPriceEntity
        ));

        List<StockPriceResponse> expected = List.of(
                new StockPriceResponse(januaryFirst, oneHundred),
                new StockPriceResponse(januarySecond, twoHundred),
                new StockPriceResponse(januaryThird, threeHundred)
        );

        // when
        List<StockPriceResponse> result = stockPriceQueryRepository.findAllByStockId(stockEntity.getId());

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }
}
