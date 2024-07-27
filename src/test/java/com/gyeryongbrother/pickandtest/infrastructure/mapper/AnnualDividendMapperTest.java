package com.gyeryongbrother.pickandtest.infrastructure.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualDividendResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnnualDividendMapperTest {

    private AnnualDividendMapper annualDividendMapper;

    @BeforeEach
    void setup() {
        annualDividendMapper = new AnnualDividendMapper();
    }

    @Test
    void dividendsToAnnualDividends() {
        //given
        LocalDate date1 = LocalDate.of(2020, 3, 1);
        LocalDate date2 = LocalDate.of(2020, 6, 1);
        LocalDate date3 = LocalDate.of(2021, 4, 1);
        BigDecimal amount1 = BigDecimal.valueOf(0.22);
        BigDecimal amount2 = BigDecimal.valueOf(0.23);
        BigDecimal amount3 = BigDecimal.valueOf(0.32);
        Dividend dividend1 = dividend(date1, amount1);
        Dividend dividend2 = dividend(date2, amount2);
        Dividend dividend3 = dividend(date3, amount3);
        List<Dividend> dividends = new ArrayList<Dividend>();
        dividends.add(dividend1);
        dividends.add(dividend2);
        dividends.add(dividend3);
        List<AnnualDividendResponse> expected = new ArrayList<AnnualDividendResponse>();
        expected.add(new AnnualDividendResponse(2020, BigDecimal.valueOf(0.45)));
        expected.add(new AnnualDividendResponse(2021, BigDecimal.valueOf(0.32)));

        //when
        List<AnnualDividendResponse> result = annualDividendMapper.DividendsToAnnualDividends(dividends);

        //then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    private Dividend dividend(LocalDate date, BigDecimal amount) {
        return Dividend.builder()
                .date(date)
                .amount(amount)
                .build();
    }
}
