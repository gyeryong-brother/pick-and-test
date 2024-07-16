package com.gyeryongbrother.pickandtest.infrastructure.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponseFixture;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DividendFetcherDataMapperTest {

    private DividendFetcherDataMapper dividendFetcherDataMapper;

    @BeforeEach
    void setUp() {
        dividendFetcherDataMapper = new DividendFetcherDataMapper();
    }

    @Test
    void dividendResponseToDividends() {
        // given
        DividendResponse dividendResponse = DividendResponseFixture.dividendResponse();
        List<Dividend> expected = List.of(
                dividend(8, 0.24),
                dividend(5, 0.24),
                dividend(2, 0.23)
        );

        // when
        List<Dividend> result = dividendFetcherDataMapper.dividendResponseToDividends(dividendResponse);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    private Dividend dividend(int month, double amount) {
        return Dividend.builder()
                .date(LocalDate.of(2023, month, 10))
                .amount(BigDecimal.valueOf(amount))
                .build();
    }
}
