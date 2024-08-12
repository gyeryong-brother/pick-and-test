package com.gyeryongbrother.pickandtest.domain.core;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class FavoriteStock {

    private final Long id;
    private final Long memberId;
    private final StockDetail stockDetail;
}
