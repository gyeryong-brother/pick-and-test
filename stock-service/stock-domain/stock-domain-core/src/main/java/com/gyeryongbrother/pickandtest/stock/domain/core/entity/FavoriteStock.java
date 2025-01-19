package com.gyeryongbrother.pickandtest.stock.domain.core.entity;

import java.util.Objects;
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

    public void validateCanDeleteBy(Long memberId) {
        if (Objects.equals(this.memberId, memberId)) {
            return;
        }
        throw new IllegalArgumentException("can not delete favorite stock");
    }
}
