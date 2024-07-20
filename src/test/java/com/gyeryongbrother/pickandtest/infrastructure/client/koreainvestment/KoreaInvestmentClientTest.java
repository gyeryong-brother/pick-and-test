package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchangeCode.NASDAQ_CODE;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponseFixture.appleStockResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.ContinuityCode.NEXT;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.Period.DAY;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBodyFixture.appleFirstStockPriceBody;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchangeCode;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.StockPriceFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
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
        StockResponse appleStockResponse = appleStockResponse();
        given(stockFetcher.fetchStock(any(StockExchangeCode.class), anyString()))
                .willReturn(appleStockResponse);

        // when
        StockResponse result = koreaInvestmentClient.fetchStock(NASDAQ, "AAPL");

        // then
        assertThat(result).isEqualTo(appleStockResponse);
    }

    @Test
    void fetchStockPrice() {
        // given
        LocalDate date = LocalDate.of(2024, 1, 1);
        StockPriceResponse stockPriceResponse = new StockPriceResponse(NEXT, appleFirstStockPriceBody());
        given(stockPriceFetcher.fetchStockPrice(NASDAQ_CODE, "AAPL", DAY, date))
                .willReturn(stockPriceResponse);

        // when
        StockPriceResponse result = koreaInvestmentClient.fetchStockPrice(NASDAQ, "AAPL", date);

        // then
        assertThat(result).isEqualTo(stockPriceResponse);
    }
}
