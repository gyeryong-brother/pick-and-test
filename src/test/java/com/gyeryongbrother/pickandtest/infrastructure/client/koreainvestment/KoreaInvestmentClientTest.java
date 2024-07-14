package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponseFixture.actualFetchStockResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBodyFixture.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.ContinuityCode;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.Period;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.StockPriceFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponse;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class KoreaInvestmentClientTest {

    @Mock
    private StockFetcher stockFetcher;

    @Mock
    private StockPriceFetcher stockPriceFetcher;

    private KoreaInvestmentClient koreaInvestmentClient;

    @BeforeEach
    void setUp() {
        koreaInvestmentClient = new KoreaInvestmentClient(stockFetcher, stockPriceFetcher);
    }

    @Test
    void fetchStock() {
        // given
        FetchStockResponse fetchStockResponse = actualFetchStockResponse();
        given(stockFetcher.fetchStock(any(StockExchange.class), anyString()))
                .willReturn(fetchStockResponse);

        // when
        FetchStockResponse result = koreaInvestmentClient.fetchStock(NASDAQ, "AAPL");

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(fetchStockResponse);
    }

    @Test
    void fetchStockPrice() {
        // given
        LocalDate date = LocalDate.of(2024, 1, 1);
        FetchStockPriceResponse fetchStockPriceResponse = new FetchStockPriceResponse(ContinuityCode.NEXT, empty());
        given(stockPriceFetcher.fetchStockPrice(NASDAQ, "AAPL", Period.DAY, date))
                .willReturn(fetchStockPriceResponse);

        // when
        FetchStockPriceResponse result = koreaInvestmentClient.fetchStockPrice(NASDAQ, "AAPL", date);

        // then
        assertThat(result).isEqualTo(fetchStockPriceResponse);
    }
}
