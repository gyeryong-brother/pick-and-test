package com.gyeryongbrother.pickandtest.stock.domain.core.entity;

import java.util.Objects;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class FavoriteStock {

    private final Long id;
    private final Long memberId;
    private final Stock stock;

    public boolean canDeleteBy(Long memberId) {
        return Objects.equals(this.memberId, memberId);
    }

    public Long id() {
        return id;
    }

    public Long memberId() {
        return memberId;
    }

    public Stock stock() {
        return stock;
    }
}
