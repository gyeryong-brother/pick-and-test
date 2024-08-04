package com.gyeryongbrother.pickandtest.domain.core;

import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.DividendFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Dividends 클래스의 메서드를 실행한다")
public class DividendsTest {

    @Test
    @DisplayName("Dividends 클래스의 메서드를 사용하여 List<AnnualDividend>을 얻는다")
    void getAnnualDividends() {

        //given
        Dividends dividends = new Dividends(DividendFixture.appleDividendsAtVariousYear());
        List<AnnualDividend> expected = List.of(
                new AnnualDividend(2020, BigDecimal.valueOf(0.45)),
                new AnnualDividend(2021, BigDecimal.valueOf(0.32))
        );

        //when
        List<AnnualDividend> result = dividends.getAnnualDividends();

        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(result);
    }
}
