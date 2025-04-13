package com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment;

import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment.stock.dto.StockResponseFixture.appleStockResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment.common.StockExchangeCode;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment.stock.StockFetcher;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment.stock.dto.StockResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("한국투자증권 api 를 사용한다")
class KoreaInvestmentClientTest {

    @Mock
    private StockFetcher stockFetcher;

    private KoreaInvestmentClient koreaInvestmentClient;

    @BeforeEach
    void setUp() {
        koreaInvestmentClient = new KoreaInvestmentClient(stockFetcher);
    }

    @Test
    @DisplayName("주식 정보를 가져온다")
    void fetchStock() {
        // given
        StockResponse appleStockResponse = appleStockResponse();
        given(stockFetcher.fetchStock(any(StockExchangeCode.class), anyString()))
                .willReturn(appleStockResponse);

        // when
        StockResponse result = koreaInvestmentClient.fetchStock(NASDAQ, "AAPL");

        // then
        assertThat(result).isEqualTo(appleStockResponse);
    }
}
