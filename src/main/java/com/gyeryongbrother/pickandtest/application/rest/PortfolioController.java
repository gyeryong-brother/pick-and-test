package com.gyeryongbrother.pickandtest.application.rest;

import com.gyeryongbrother.pickandtest.domain.service.dto.PortfolioResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.PortfolioStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.PortfolioQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/portfolios")
public class PortfolioController {

    private final PortfolioQueryService portfolioQueryService;

    @GetMapping("/{portfolioId}")
    ResponseEntity<List<PortfolioStockResponse>> findAllPortfolioStocks(@PathVariable Long portfolioId) {
        List<PortfolioStockResponse> portfolioStockResponses =
                portfolioQueryService.findAllByPortfolioId(portfolioId);
        return ResponseEntity.ok(portfolioStockResponses);
    }

    @GetMapping
    ResponseEntity<List<PortfolioResponse>> findAllPortfolios() {
        List<PortfolioResponse> portfolioResponses = portfolioQueryService.findAllPortfolios();
        return ResponseEntity.ok(portfolioResponses);
    }
}
