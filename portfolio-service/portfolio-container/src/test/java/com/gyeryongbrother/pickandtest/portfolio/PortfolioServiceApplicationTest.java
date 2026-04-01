package com.gyeryongbrother.pickandtest.portfolio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.mock.mockito.MockBean;
@SpringBootTest
@DisplayName("어플리케이션을 실행한다")
class PortfolioServiceApplicationTest {

    @MockBean
    private RedissonClient redissonClient;
    @Test
    @DisplayName("어플리케이션을 실행한다")
    void contextLoads() {
    }
}
