package com.gyeryongbrother.pickandtest.portfolio.domain.service;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.exception.CouponCoreException;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.CouponIssueCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input.CouponIssueApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedissonCouponIssueFacade implements CouponIssueApplicationService {

    private final RedissonClient redissonClient;
    private final CouponIssueServiceImpl couponIssueService;

    @Override
    public void issueSubscriptionCoupon(CouponIssueCommand command) {
        String lockKey = "lock:coupon:" + command.getCouponId();
        RLock lock = redissonClient.getLock(lockKey);

        try {
            boolean isLocked = lock.tryLock(10, 3, TimeUnit.SECONDS);

            if (!isLocked) {
                log.error("Failed to acquire Redisson lock for key: {}", lockKey);
                throw new CouponCoreException("Unable to acquire lock, resource is busy.");
            }

            couponIssueService.issueSubscriptionCoupon(command);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CouponCoreException("Thread interrupted while waiting for lock", e);
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
