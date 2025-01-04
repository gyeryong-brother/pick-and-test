package com.gyeryongbrother.pickandtest.stock.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividend;

public interface DividendRepository {

    Dividend save(Dividend dividend);
}
