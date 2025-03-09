package com.gyeryongbrother.pickandtest.stockprice.application.rest;

import com.gyeryongbrother.pickandtest.stockprice.domain.service.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.StockPriceQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock-prices")
public class StockPriceController {

    private final StockPriceQueryService stockPriceQueryService;

    @GetMapping
    ResponseEntity<List<StockPriceResponse>> findAllStockPrices(@RequestParam Long stockId) {
        List<StockPriceResponse> responses = stockPriceQueryService.findAllByStockId(stockId);
        return ResponseEntity.ok(responses);
    }
}
