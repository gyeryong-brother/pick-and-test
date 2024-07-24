package com.gyeryongbrother.pickandtest.domain.service.adapter;

import com.gyeryongbrother.pickandtest.domain.service.ports.input.GetHistory;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.AnnualDividend;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DividendController {

    private final GetHistory getHistory;

    @GetMapping("/{name}/dividend")
    ResponseEntity<List<AnnualDividend>> annualDividendsByName(@PathVariable String name) {
        return ResponseEntity.ok(getHistory.getAnnualDividendHistoryByName(name));
    }
}
