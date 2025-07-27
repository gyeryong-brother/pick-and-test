package com.gyeryongbrother.pickandtest.stockprice.domain.service;

import static com.gyeryongbrother.pickandtest.stockprice.domain.fixture.entity.StockPriceFixture.januaryStockPrices;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.stockprice.domain.service.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.StockPriceQueryService;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceQueryRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("주가 조회 서비스를 구현한다")
class StockPriceQueryServiceImplTest {

    @Mock
    private StockPriceQueryRepository stockPriceQueryRepository;

    private StockPriceQueryService stockPriceQueryService;

    @BeforeEach
    void setUp() {
        stockPriceQueryService = new StockPriceQueryServiceImpl(stockPriceQueryRepository);
    }

    @Test
    @DisplayName("주식 아이디로 주가들을 조회한다")
    void findAllByStockId() {
        // given
        List<StockPriceResponse> expected = List.of(
                new StockPriceResponse(LocalDate.of(2024, 1, 1), BigDecimal.valueOf(100)),
                new StockPriceResponse(LocalDate.of(2024, 1, 2), BigDecimal.valueOf(200)),
                new StockPriceResponse(LocalDate.of(2024, 1, 3), BigDecimal.valueOf(300))
        );
        given(stockPriceQueryRepository.findAllByStockId(anyLong()))
                .willReturn(januaryStockPrices());

        // when
        List<StockPriceResponse> result = stockPriceQueryService.findAllByStockId(1L);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
