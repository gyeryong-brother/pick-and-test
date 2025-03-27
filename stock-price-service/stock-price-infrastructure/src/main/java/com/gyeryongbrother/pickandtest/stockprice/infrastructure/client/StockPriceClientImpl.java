package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.KoreaInvestmentClient;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.common.DateProvider;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.StockPriceAssembler;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.dto.StockPriceResponse;
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

    @Override
    public List<StockPrice> fetchStockPrices(Stock stock, LocalDate startDate) {
        StockPriceAssembler stockPriceAssembler = new StockPriceAssembler(startDate, dateProvider,
                fetchingFunction(stock));
        return stockPriceAssembler.assemble(stock.id());
    }

    private Function<LocalDate, StockPriceResponse> fetchingFunction(Stock stock) {
        return date -> koreaInvestmentClient.fetchOneHundredStockPricesBeforeDate(stock.stockExchange(), stock.symbol(),
                date);
    }
}
