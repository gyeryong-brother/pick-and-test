package com.gyeryongbrother.pickandtest.stock.domain.service;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.DividendFixture.appleDividendsAtVariousYear;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.stock.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.DividendQueryService;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.DividendQueryRepository;
import java.math.BigDecimal;
import java.util.List;
import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
@DisplayName("배당 api를 제공한다")
class DividendQueryServiceImplTest {

    @Mock
    private DividendQueryRepository dividendQueryRepository;

    private DividendQueryService dividendQueryService;

    @BeforeEach
    void setUp() {
        dividendQueryService = new DividendQueryServiceImpl(dividendQueryRepository);
    }

    @Test
    @DisplayName("Id를 사용하여 AnnualDividends를 불러옴")
    void getAnnualDividendsById() {
        //given
        given(dividendQueryRepository.findAllByStockId(anyLong()))
                .willReturn(appleDividendsAtVariousYear());
        List<AnnualDividendResponse> expected = List.of(
                new AnnualDividendResponse(2020, BigDecimal.valueOf(0.45)),
                new AnnualDividendResponse(2021, BigDecimal.valueOf(0.32))
        );

        //when
        List<AnnualDividendResponse> result = dividendQueryService.getAnnualDividendsById(1L);

        //then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }
}
