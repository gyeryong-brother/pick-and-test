package com.gyeryongbrother.pickandtest.application.rest;

import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.DividendQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.StockPriceQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.StockQueryService;
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

    @GetMapping
    ResponseEntity<List<StockResponse>> searchStocks(@RequestParam String keyword) {
        List<StockResponse> stockResponses = stockQueryService.findAllByNameOrSymbol(keyword);
        return ResponseEntity.ok(stockResponses);
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
}
