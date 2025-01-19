package com.gyeryongbrother.pickandtest.stock.application.rest;

import com.gyeryongbrother.pickandtest.stock.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.AnnualIncomeStatementResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.MarketCapitalizationResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.StockDetailResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.StockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.DividendQueryService;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.IncomeStatementQueryService;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.StockPriceQueryService;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.StockQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {

    private final StockQueryService stockQueryService;
    private final DividendQueryService dividendQueryService;
    private final StockPriceQueryService stockPriceQueryService;
    private final IncomeStatementQueryService incomeStatementQueryService;

    @GetMapping
    ResponseEntity<List<StockResponse>> searchStocks(@RequestParam String keyword) {
        List<StockResponse> stockResponses = stockQueryService.findAllByNameOrSymbol(keyword);
        return ResponseEntity.ok(stockResponses);
    }

    @GetMapping("/{stockId}")
    ResponseEntity<StockDetailResponse> findStockById(@PathVariable Long stockId) {
        StockDetailResponse response = stockQueryService.findStockById(stockId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{stockId}/prices")
    ResponseEntity<List<StockPriceResponse>> findAllStockPrices(@PathVariable Long stockId) {
        List<StockPriceResponse> stockPriceResponses = stockPriceQueryService.findAllByStockId(stockId);
        return ResponseEntity.ok(stockPriceResponses);
    }

    @GetMapping("/{stockId}/dividends")
    ResponseEntity<List<AnnualDividendResponse>> findAnnualDividendsById(@PathVariable Long stockId) {
        return ResponseEntity.ok(dividendQueryService.getAnnualDividendsById(stockId));
    }

    @GetMapping("/{stockId}/market-capitalizations")
    ResponseEntity<List<MarketCapitalizationResponse>> findAllMarketCapitalizations(
            @PathVariable Long stockId
    ) {
        List<MarketCapitalizationResponse> marketCapitalizationResponses =
                stockQueryService.findAllMarketCapitalizationsByStockId(stockId);
        return ResponseEntity.ok(marketCapitalizationResponses);
    }

    @GetMapping("/{stockId}/incomeStatements")
    ResponseEntity<List<AnnualIncomeStatementResponse>> findAnnualIncomeStatementsById(@PathVariable Long stockId) {
        return ResponseEntity.ok(incomeStatementQueryService.getAnnualIncomeStatementsById(stockId));
    }
}
