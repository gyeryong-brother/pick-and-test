package com.gyeryongbrother.pickandtest.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.domain.service.dto.StockResponse;

import java.util.List;

public interface StockQueryService {

    List<StockResponse> findAllByNameOrSymbol(String keyword);
}
