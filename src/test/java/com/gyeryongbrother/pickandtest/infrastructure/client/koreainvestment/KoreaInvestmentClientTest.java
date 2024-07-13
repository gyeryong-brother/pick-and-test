package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponseFixture.actualFetchStockResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class KoreaInvestmentClientTest {

    @Mock
    private StockFetcher stockFetcher;

    private KoreaInvestmentClient koreaInvestmentClient;

    @BeforeEach
    void setUp() {
        koreaInvestmentClient = new KoreaInvestmentClient(stockFetcher);
    }

    @Test
    void fetchStock() {
        // given
        FetchStockResponse fetchStockResponse = actualFetchStockResponse();
        given(stockFetcher.fetchStock(any(StockExchange.class), anyString()))
                .willReturn(fetchStockResponse);
        Stock expected = Stock.builder()
                .name("APPLE INC")
                .symbol("AAPL")
                .listingDate(null)
                .build();

        // when
        Stock result = koreaInvestmentClient.fetchStock("AAPL");

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
