package com.gyeryongbrother.pickandtest.portfolio.domain.service;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.SubscriptionCoupon;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.UserCoupon;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.exception.CouponCoreException;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.CouponIssueCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.repository.SubscriptionCouponRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.repository.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponIssueServiceImpl {

    private final SubscriptionCouponRepository subscriptionCouponRepository;
    private final UserCouponRepository userCouponRepository;

    @Transactional
    public void issueSubscriptionCoupon(CouponIssueCommand command) {
        Long couponId = command.getCouponId();
        Long userId = command.getUserId();

        if (userCouponRepository.existsByUserIdAndCouponId(userId, couponId)) {
            throw new CouponCoreException("User already has this coupon.");
        }

        SubscriptionCoupon coupon = subscriptionCouponRepository.findById(couponId)
                .orElseThrow(() -> new CouponCoreException("Coupon not found."));

        coupon.issue();
        subscriptionCouponRepository.save(coupon);

        UserCoupon userCoupon = UserCoupon.create(userId, couponId);
        userCouponRepository.save(userCoupon);

        log.info("Successfully issued coupon {} to user {}", couponId, userId);
    }
}
