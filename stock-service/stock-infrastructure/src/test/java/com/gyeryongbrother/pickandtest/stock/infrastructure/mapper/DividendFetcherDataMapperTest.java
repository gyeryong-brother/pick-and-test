package com.gyeryongbrother.pickandtest.stock.infrastructure.mapper;

import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.dividend.DividendFixture.appleDividends;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.dividend.dto.DividendResponseFixture.appleDividendResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividend;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.dividend.dto.DividendResponse;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("배당 응답과 배당을 매핑한다")
class DividendFetcherDataMapperTest {

    private DividendFetcherDataMapper dividendFetcherDataMapper;

    @BeforeEach
    void setUp() {
        dividendFetcherDataMapper = new DividendFetcherDataMapper();
    }

    @Test
    @DisplayName("배당 응답을 배당들로 변환한다")
    void dividendResponseToDividends() {
        // given
        DividendResponse appleDividendResponse = appleDividendResponse();
        List<Dividend> expected = appleDividends();

        // when
        List<Dividend> result = dividendFetcherDataMapper.dividendResponseToDividends(appleDividendResponse);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }
}
