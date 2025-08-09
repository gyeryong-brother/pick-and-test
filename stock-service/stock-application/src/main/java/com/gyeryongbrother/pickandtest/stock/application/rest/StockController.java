package com.gyeryongbrother.pickandtest.stock.application.rest;

import com.gyeryongbrother.pickandtest.stock.domain.service.dto.AnnualIncomeStatementResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.StockDetailResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.StockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.IncomeStatementQueryService;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.StockCollector;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.StockQueryService;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.StockScheduler;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
@Slf4j
public class StockController {

    private final StockQueryService stockQueryService;
    private final IncomeStatementQueryService incomeStatementQueryService;
    private final StockCollector stockCollector;
    private final StockScheduler stockScheduler;

    @GetMapping("/collect")
    ResponseEntity<Void> collectStocks() {
        log.info("collect stock started!!");
        stockCollector.collectStocks();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/collect-prices")
    ResponseEntity<Void> collectStockPrices() {
        log.info("collect stock price started!!");
        stockScheduler.collectStockPrices();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/collect-minute-prices")
    ResponseEntity<Void> collectMinutePrices() {
        log.info("collect stock minute price started!!");
        stockScheduler.collectStockMinutePrices();
        return ResponseEntity.ok().build();
    }

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

    @GetMapping("/{stockId}/incomeStatements")
    ResponseEntity<List<AnnualIncomeStatementResponse>> findAnnualIncomeStatementsById(@PathVariable Long stockId) {
        return ResponseEntity.ok(incomeStatementQueryService.getAnnualIncomeStatementsById(stockId));
    }
}
