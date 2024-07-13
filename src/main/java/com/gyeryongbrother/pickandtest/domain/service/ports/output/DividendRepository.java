package com.gyeryongbrother.pickandtest.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.domain.core.Dividend;

public interface DividendRepository {

    Dividend save(Dividend dividend);
}
