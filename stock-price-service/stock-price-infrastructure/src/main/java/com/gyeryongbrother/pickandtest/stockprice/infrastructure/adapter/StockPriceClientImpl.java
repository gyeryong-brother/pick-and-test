package com.gyeryongbrother.pickandtest.stockprice.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockClient;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceClient;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.KoreaInvestmentClient;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceBody;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.time.LocalDate;
import java.util.List;
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
        StockPriceAssembler stockPriceAssembler = new StockPriceAssembler(date, dateProvider);
        while (stockPriceAssembler.hasNext()) {
            LocalDate nextDate = stockPriceAssembler.getNextDate();
            StockPriceResponse stockPriceResponse = koreaInvestmentClient.fetchStockPrice(stock.stockExchange(),
                    stock.symbol(), nextDate);
            stockPriceAssembler.add(stockPriceResponse);
        }
        return stockPriceAssembler.getStockPriceResponses().stream()
                .map(StockPriceResponse::stockPriceBody)
                .map(StockPriceBody::stockPriceDetails)
                .flatMap(List::stream)
                .map(it -> it.toDomain(stockId))
                .toList();
    }
}
