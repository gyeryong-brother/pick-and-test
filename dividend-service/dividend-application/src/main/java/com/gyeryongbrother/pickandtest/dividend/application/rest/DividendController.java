package com.gyeryongbrother.pickandtest.dividend.application.rest;

import com.gyeryongbrother.pickandtest.dividend.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.input.DividendQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dividends")
public class DividendController {

    private final DividendQueryService dividendQueryService;

    @GetMapping
    ResponseEntity<List<AnnualDividendResponse>> findAnnualDividendsByStockId(@RequestParam Long stockId) {
        List<AnnualDividendResponse> response = dividendQueryService.findAnnualDividendsByStockId(stockId);
        return ResponseEntity.ok(response);
    }
}
