package com.gyeryongbrother.pickandtest.dividend.dataaccess.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.dividend.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.output.DividendRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("배당 레포지토리를 구현한다")
class DividendRepositoryImplTest {

    @Autowired
    private DividendRepository dividendRepository;

    @Test
    @DisplayName("배당을 저장한다")
    void save() {
        //given
        LocalDate januaryFirst = LocalDate.of(2024, 1, 1);
        BigDecimal amount = BigDecimal.valueOf(100);
        Dividend dividend = new Dividend(null, 1L, januaryFirst, amount);
        Dividend expected = new Dividend(null, 1L, januaryFirst, amount);

        //when
        Dividend result = dividendRepository.save(dividend);

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
