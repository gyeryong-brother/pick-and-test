package com.gyeryongbrother.pickandtest.portfolio.application.rest;

import static org.springframework.http.HttpStatus.CREATED;

import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.PortfolioResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.PortfolioStockResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioRequest;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input.PortfolioQueryService;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input.PortfolioService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @PutMapping("/{portfolioId}/update")
    ResponseEntity<UpdatePortfolioResponse> updatePortfolio(
            @PathVariable Long portfolioId,
            @RequestBody UpdatePortfolioRequest updatePortfolioRequest
    ){
        UpdatePortfolioCommand updatePortfolioCommand=updatePortfolioRequest.toCommand(portfolioId);
        UpdatePortfolioResponse updatePortfolioResponse=portfolioService.updatePortfolio(updatePortfolioCommand);
        return ResponseEntity.ok(updatePortfolioResponse);
    }
}
