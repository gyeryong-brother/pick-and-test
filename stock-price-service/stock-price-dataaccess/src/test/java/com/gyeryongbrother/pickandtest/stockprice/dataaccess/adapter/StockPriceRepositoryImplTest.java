package com.gyeryongbrother.pickandtest.stockprice.dataaccess.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.gyeryongbrother.pickandtest.stockprice.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("주가 레포지토리를 구현한다")
class StockPriceRepositoryImplTest {

    @Autowired
    private StockPriceRepository stockPriceRepository;

    @Test
    @DisplayName("주가를 저장한다")
    void save() {
        //given
        LocalDate januaryFirst = LocalDate.of(2024, 1, 1);
        BigDecimal price = BigDecimal.valueOf(100);
        StockPrice stockPrice = new StockPrice(null, 1L, januaryFirst, price);
        StockPrice expected = new StockPrice(null, 1L, januaryFirst, price);

        //when
        StockPrice result = stockPriceRepository.save(stockPrice);

        //then
        assertAll(
                () -> assertThat(result.id()).isPositive(),
                () -> assertThat(result).usingRecursiveComparison()
                        .ignoringExpectedNullFields()
                        .isEqualTo(expected)
        );
    }
}
