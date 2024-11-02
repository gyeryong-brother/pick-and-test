package com.gyeryongbrother.pickandtest.application.rest;

import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import com.gyeryongbrother.pickandtest.domain.service.dto.PortfolioResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.PortfolioStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.UpdatePortfolioCommand;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.PortfolioQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.PortfolioService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/portfolios")
public class PortfolioController {

    private final PortfolioQueryService portfolioQueryService;

    private final PortfolioService portfolioService;

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

    @PutMapping("/update")
    ResponseEntity<List<PortfolioStockResponse>> updatePortfolio(
            @RequestBody UpdatePortfolioCommand updatePortfolioCommand
    ) {

        List<PortfolioStock> updatedPortfolioStocks =
                portfolioService.updatePortfolioStocks(updatePortfolioCommand);
        List<PortfolioStockResponse> portfolioStockResponses = updatedPortfolioStocks.stream()
                .map(PortfolioStockResponse::from)
                .toList();
        return ResponseEntity.ok(portfolioStockResponses);
    }
}
