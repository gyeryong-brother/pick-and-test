package com.gyeryongbrother.pickandtest.infrastructure.adapter;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange.NASDAQ;

import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.KoreaInvestmentClient;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponse;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockPriceAssembler {

    private final KoreaInvestmentClient koreaInvestmentClient;

    public List<StockPrice> getStockPrices(String symbol) {
        AssembleStockPriceResult assembleStockPriceResult = new AssembleStockPriceResult();
        while (assembleStockPriceResult.hasNext()) {
            LocalDate nextDate = assembleStockPriceResult.getNextDate();
            FetchStockPriceResponse fetchStockPriceResponse = fetchStockPriceInNasdaq(symbol, nextDate);
            assembleStockPriceResult.add(fetchStockPriceResponse);
        }
        return assembleStockPriceResult.getStockPrices();
    }

    private FetchStockPriceResponse fetchStockPriceInNasdaq(String symbol, LocalDate date) {
        return koreaInvestmentClient.fetchStockPrice(NASDAQ, symbol, date);
    }
}
