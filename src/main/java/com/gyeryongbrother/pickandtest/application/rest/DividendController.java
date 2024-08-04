package com.gyeryongbrother.pickandtest.application.rest;

import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.DividendQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DividendController {

    private final DividendQueryService dividendQueryService;

    @GetMapping("/{stockId}/dividend")
    ResponseEntity<List<AnnualDividendResponse>> findAnnualDividendsById(@PathVariable Long stockId) {
        return ResponseEntity.ok(dividendQueryService.getAnnualDividendsById(stockId));
    }
}
