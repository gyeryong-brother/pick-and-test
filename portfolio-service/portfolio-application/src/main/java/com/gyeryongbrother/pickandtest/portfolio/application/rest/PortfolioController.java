package com.gyeryongbrother.pickandtest.portfolio.application.rest;

import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.DeletePortfolioCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.PortfolioResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.PortfolioStockResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.PortfoliosResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.SavePortfolioCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.PortfolioRequest;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input.PortfolioQueryService;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input.PortfolioService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/portfolios")
public class PortfolioController {

    private final PortfolioQueryService portfolioQueryService;
    private final PortfolioService portfolioService;

    @GetMapping("/{portfolioId}")
    ResponseEntity<List<PortfolioStockResponse>> findAllPortfolioStocks(
            @PathVariable Long portfolioId,
            @RequestHeader Long memberId
    ) {
        List<PortfolioStockResponse> portfolioStockResponses =
                portfolioQueryService.findAllByPortfolioId(portfolioId);
        return ResponseEntity.ok(portfolioStockResponses);
    }

    @GetMapping
    ResponseEntity<List<PortfolioResponse>> findAllPortfolios(
            @RequestHeader Long memberId
    ) {
        List<PortfolioResponse> portfolioResponses = portfolioQueryService.findAllPortfolios();
        return ResponseEntity.ok(portfolioResponses);
    }

    @PutMapping("/{portfolioId}")
    ResponseEntity<UpdatePortfolioResponse> updatePortfolio(
            @PathVariable Long portfolioId,
            @RequestBody PortfolioRequest portfolioRequest,
            @RequestHeader Long memberId
    ) {
        UpdatePortfolioCommand updatePortfolioCommand = portfolioRequest.UpdatePortfoliotoCommand(memberId,portfolioId);
        UpdatePortfolioResponse updatePortfolioResponse = portfolioService.updatePortfolio(updatePortfolioCommand);
        return ResponseEntity.ok(updatePortfolioResponse);
    }

    @PostMapping
    ResponseEntity<UpdatePortfolioResponse> savePortfolio(
            @RequestBody PortfolioRequest portfolioRequest,
            @RequestHeader Long memberId
    ) {
        SavePortfolioCommand savePortfolioCommand = portfolioRequest.SavePortfoliotoCommand(memberId);
        UpdatePortfolioResponse updatePortfolioResponse = portfolioService.savePortfolio(savePortfolioCommand);
        return ResponseEntity.ok(updatePortfolioResponse);
    }

    @DeleteMapping("/{portfolioId}")
    ResponseEntity<PortfoliosResponse> deletePortfolio(
            @PathVariable Long portfolioId,
            @RequestHeader Long memberId
    ){
        DeletePortfolioCommand deletePortfolioCommand=new DeletePortfolioCommand(memberId,portfolioId);
        PortfoliosResponse portfoliosResponse=portfolioService.deletePortfolio(deletePortfolioCommand);
        return ResponseEntity.ok(portfoliosResponse);
    }

}
