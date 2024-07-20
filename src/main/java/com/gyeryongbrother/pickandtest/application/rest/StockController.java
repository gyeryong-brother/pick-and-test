package com.gyeryongbrother.pickandtest.application.rest;

import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.StockQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {

    private final StockQueryService stockQueryService;

    @GetMapping
    ResponseEntity<List<StockResponse>> searchStocks(@RequestParam String keyword) {
        List<StockResponse> stockResponses = stockQueryService.findAllByNameOrSymbol(keyword);
        return ResponseEntity.ok(stockResponses);
    }
}
