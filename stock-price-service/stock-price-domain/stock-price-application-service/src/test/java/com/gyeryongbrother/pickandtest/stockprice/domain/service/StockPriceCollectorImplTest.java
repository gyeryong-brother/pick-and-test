package com.gyeryongbrother.pickandtest.stockprice.domain.service;

import static com.gyeryongbrother.pickandtest.stockprice.domain.fixture.entity.StockPriceFixture.stockPricesByStockId;
import static com.gyeryongbrother.pickandtest.stockprice.domain.fixture.valueobject.LocalDateFixture.januaryFirst;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.StockPriceCollector;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.MockStockPriceFetcher;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceQueryRepository;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceRepository;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("주가 정보를 가져와 저장한다")
class StockPriceCollectorImplTest {

    @Mock
    private StockPriceRepository stockPriceRepository;

    @Mock
    private StockPriceQueryRepository stockPriceQueryRepository;

    private StockPriceCollector stockPriceCollector;

    private Map<Long, List<StockPrice>> stockPricesByStockId;

    @BeforeEach
    void setUp() {
        stockPricesByStockId = stockPricesByStockId();
        stockPriceCollector = new StockPriceCollectorImpl(
                stockPriceRepository,
                stockPriceQueryRepository,
                new MockStockPriceFetcher(stockPricesByStockId)
        );
    }

    @Test
    @DisplayName("주가 정보가 없으면 모든 기간을 가져와 저장한다")
    void collectAllStockPrices() {
        // given
        List<StockPrice> stockPrices = stockPricesByStockId.get(1L);
        given(stockPriceQueryRepository.findLastDateOfStockPricesByStockId(anyLong()))
                .willReturn(null);

        // when
        stockPriceCollector.collectStockPrices(1L);

        // then
        verify(stockPriceRepository, times(1)).saveAll(any());
        verify(stockPriceRepository).saveAll(stockPrices);
    }

    @Test
    @DisplayName("주가 정보가 있으면 이후 모든 주가를 가져와 저장한다")
    void collectStockPrices() {
        // given
        given(stockPriceQueryRepository.findLastDateOfStockPricesByStockId(anyLong()))
                .willReturn(januaryFirst());

        // when
        stockPriceCollector.collectStockPrices(1L);

        // then
        verify(stockPriceRepository, times(1)).saveAll(any());
    }
}
