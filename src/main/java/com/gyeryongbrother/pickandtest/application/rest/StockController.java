package com.gyeryongbrother.pickandtest.application.rest;

import static org.springframework.http.HttpStatus.CREATED;

import com.gyeryongbrother.pickandtest.application.dto.CreateFavoriteStockRequest;
import com.gyeryongbrother.pickandtest.domain.service.dto.CreateFavoriteStockCommand;
import com.gyeryongbrother.pickandtest.domain.service.dto.CreateFavoriteStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.StockQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.StockService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {

    private final StockQueryService stockQueryService;
    private final StockService stockService;

    @GetMapping
    ResponseEntity<List<StockResponse>> searchStocks(@RequestParam String keyword) {
        List<StockResponse> stockResponses = stockQueryService.findAllByNameOrSymbol(keyword);
        return ResponseEntity.ok(stockResponses);
    }

    @PostMapping("/{stockId}/favorite")
    ResponseEntity<CreateFavoriteStockResponse> createFavoriteStock(
            @PathVariable Long stockId,
            @RequestBody CreateFavoriteStockRequest createFavoriteStockRequest
    ) {
        CreateFavoriteStockCommand createFavoriteStockCommand = createFavoriteStockRequest.toCommand(stockId);
        CreateFavoriteStockResponse createFavoriteStockResponse =
                stockService.createFavoriteStock(createFavoriteStockCommand);
        return ResponseEntity.status(CREATED)
                .body(createFavoriteStockResponse);
    }
}
