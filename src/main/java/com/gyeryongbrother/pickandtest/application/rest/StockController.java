package com.gyeryongbrother.pickandtest.application.rest;

import static org.springframework.http.HttpStatus.CREATED;

import com.gyeryongbrother.pickandtest.application.dto.CreateFavoriteStockRequest;
import com.gyeryongbrother.pickandtest.domain.core.IncomeStatement;
import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualIncomeStatementResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.CreateFavoriteStockCommand;
import com.gyeryongbrother.pickandtest.domain.service.dto.CreateFavoriteStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.FavoriteStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.MarketCapitalizationResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.DividendQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.IncomeStatementQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.StockPriceQueryService;
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
    private final DividendQueryService dividendQueryService;
    private final StockPriceQueryService stockPriceQueryService;
    private final StockService stockService;
    private final IncomeStatementQueryService incomeStatementQueryService;

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

    @GetMapping("/favorite")
    ResponseEntity<List<FavoriteStockResponse>> findAllFavoriteStocks() {
        List<FavoriteStockResponse> favoriteStockResponses = stockQueryService.findAllFavoriteStocksByMemberId(1L);
        return ResponseEntity.ok(favoriteStockResponses);
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
    ResponseEntity<List<AnnualIncomeStatementResponse>> findAnnualIncomeStatementsById(@PathVariable Long stockId){
        return ResponseEntity.ok(incomeStatementQueryService.getAnnualIncomeStatementsById(stockId));
    }
}
