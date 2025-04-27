package com.gyeryongbrother.pickandtest.dividend.infrastructure.api.gyeryongbrother.dto;

import java.util.List;

public record DividendsResponse(
        List<DividendResponse> dividends
) {
}
