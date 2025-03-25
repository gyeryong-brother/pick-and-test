package com.gyeryongbrother.pickandtest.stockprice.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockClient;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceClient;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.KoreaInvestmentClient;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockPriceClientImpl implements StockPriceClient {

    private final KoreaInvestmentClient koreaInvestmentClient;
    private final DateProvider dateProvider;
    private final StockClient stockClient;

    @Override
    public List<StockPrice> fetchStockPrices(Long stockId, LocalDate date) {
        Stock stock = stockClient.fetchStock(stockId);
        StockPriceAssembler stockPriceAssembler = new StockPriceAssembler(date, dateProvider, fetchingFunction(stock));
        return stockPriceAssembler.assemble(stockId);
    }

    private Function<LocalDate, StockPriceResponse> fetchingFunction(Stock stock) {
        return it -> koreaInvestmentClient.fetchStockPrice(
                stock.stockExchange(),
                stock.symbol(),
                it
        );
    }
}
