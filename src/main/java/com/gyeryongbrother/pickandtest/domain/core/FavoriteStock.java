package com.gyeryongbrother.pickandtest.domain.core;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FavoriteStock {

    private final Long id;
    private final Long memberId;
    private final Stock stock;
}
