package com.gyeryongbrother.pickandtest.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.StockQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockQueryRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("주식 조회 서비스를 구현한다")
class StockQueryServiceImplTest {

    @Mock
    private StockQueryRepository stockQueryRepository;

    private StockQueryService stockQueryService;

    @BeforeEach
    void setUp() {
        stockQueryService = new StockQueryServiceImpl(stockQueryRepository);
    }

    @Test
    @DisplayName("이름이나 심볼로 주식들을 조회한다")
    void findAllByNameOrSymbol() {
        // given
        List<StockResponse> stockResponses = List.of(
                new StockResponse(1L, "Advance Auto Parts, Inc.", "AAP"),
                new StockResponse(2L, "Apple Inc.", "AAPL"),
                new StockResponse(3L, "AAP, Inc.", "AAPJ")
        );
        given(stockQueryRepository.findAllByNameOrSymbol(anyString()))
                .willReturn(stockResponses);

        // when
        List<StockResponse> result = stockQueryService.findAllByNameOrSymbol("AAP");

        // then
        assertThat(result).isEqualTo(stockResponses);
    }
}
