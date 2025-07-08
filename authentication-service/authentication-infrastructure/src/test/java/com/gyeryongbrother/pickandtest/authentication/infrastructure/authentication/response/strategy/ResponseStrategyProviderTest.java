package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@DisplayName("ResponseStrategyProvider 는 LoginType 별로 ResponseStrategy 를 매핑해 관리하고 LoginType 에 맞는 Strategy 를 반환한다")
class ResponseStrategyProviderTest {

    private ResponseStrategy responseStrategy;
    private ResponseStrategyProvider responseStrategyProvider;

    @BeforeEach
    void setUp() {

    }
}
