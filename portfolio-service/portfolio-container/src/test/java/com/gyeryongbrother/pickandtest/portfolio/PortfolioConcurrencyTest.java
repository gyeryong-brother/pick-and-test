package com.gyeryongbrother.pickandtest.portfolio;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.PortfolioJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.PortfolioServiceImpl;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PortfolioConcurrencyTest {

    @Autowired
    private PortfolioServiceImpl portfolioService;

    @MockBean
    private RedissonClient redissonClient;

    @Autowired
    private PortfolioJpaRepository portfolioJpaRepository;

    private Long savedPortfolioId;
    private final Long memberId = 1L;

    @BeforeEach
    void setUp() {
        PortfolioEntity portfolio = PortfolioEntity.builder()
                .memberId(memberId)
                .virtualInvestment(1000L)
                .build();
        savedPortfolioId = portfolioJpaRepository.save(portfolio).getId();
    }

    @AfterEach
    void tearDown() {
        portfolioJpaRepository.deleteAll();
    }

    @Test
    @DisplayName("Pessimistic Lock: 100 concurrent requests to deduct investment")
    void pessimisticLockConcurrencyTest() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    portfolioService.deductVirtualInvestment(savedPortfolioId, memberId, 10L);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        PortfolioEntity afterDeductions = portfolioJpaRepository.findById(savedPortfolioId).orElseThrow();
        assertThat(afterDeductions.getVirtualInvestment()).isEqualTo(0L);
    }
}
