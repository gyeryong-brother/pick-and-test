package com.gyeryongbrother.pickandtest.infrastructure.adapter;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponseFixture.actualFetchStockResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.KoreaInvestmentClient;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StockProviderImplTest {

    @Mock
    private KoreaInvestmentClient koreaInvestmentClient;

    private StockProvider stockProvider;

    @BeforeEach
    void setUp() {
        stockProvider = new StockProviderImpl(koreaInvestmentClient);
    }

    @Test
    void provide() {
        // given
        FetchStockResponse fetchStockResponse = actualFetchStockResponse();
        given(koreaInvestmentClient.fetchStock(any(StockExchange.class), anyString()))
                .willReturn(fetchStockResponse);
        Stock expected = Stock.builder()
                .name("APPLE INC")
                .symbol("AAPL")
                .listingDate(null)
                .build();

        // when
        Stock result = stockProvider.provide("AAPL");

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
