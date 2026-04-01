package com.gyeryongbrother.pickandtest.portfolio;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.SubscriptionCouponEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.SubscriptionCouponJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.UserCouponJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.CouponIssueCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input.CouponIssueApplicationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled("Redis 분산락을 사용하는 테스트 클래스로, 실행 시 로컬 Redis 인프라가 필요합니다.")
@SpringBootTest
@ActiveProfiles("test")
class CouponConcurrencyIntegrationTest {

    @Autowired
    private CouponIssueApplicationService couponIssueApplicationService;

    @Autowired
    private SubscriptionCouponJpaRepository couponRepository;

    @Autowired
    private UserCouponJpaRepository userCouponRepository;

    private Long savedCouponId;

    @BeforeEach
    void setUp() {
        SubscriptionCouponEntity coupon = SubscriptionCouponEntity.builder()
                .name("Premium 1-Year Subscription Coupon")
                .totalQuantity(100)
                .remainingQuantity(100)
                .expiresAt(LocalDateTime.now().plusDays(30))
                .active(true)
                .build();
        savedCouponId = couponRepository.save(coupon).getId();
    }

    @AfterEach
    void tearDown() {
        userCouponRepository.deleteAllInBatch();
        couponRepository.deleteAllInBatch();
    }

    @Disabled("Redis 분산락을 사용하는 테스트로, 실행 시 로컬 Redis 인프라가 필요합니다.")
    @Test
    @DisplayName("100개의 쓰레드로 1000명의 유저가 동시에 쿠폰 100장을 요청할 때 초과발급 방지 검증")
    void testCouponIssueConcurrencyWithRedissonLock() throws InterruptedException {
        int threadCount = 100;
        int requestCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(requestCount);
        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < requestCount; i++) {
            long userId = i + 1;
            executorService.submit(() -> {
                try {
                    CouponIssueCommand command = CouponIssueCommand.builder()
                            .couponId(savedCouponId)
                            .userId(userId)
                            .build();
                    couponIssueApplicationService.issueSubscriptionCoupon(command);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    failCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        long endTime = System.currentTimeMillis();
        System.out.println("Total Execution Time: " + (endTime - startTime) + "ms");
        System.out.println("Success Count: " + successCount.get());
        System.out.println("Fail Count: " + failCount.get());

        SubscriptionCouponEntity updatedCoupon = couponRepository.findById(savedCouponId).orElseThrow();
        long issuedUserCount = userCouponRepository.count();

        assertThat(updatedCoupon.getRemainingQuantity()).isEqualTo(0);
        assertThat(issuedUserCount).isEqualTo(100);
        assertThat(successCount.get()).isEqualTo(100);
    }
}
