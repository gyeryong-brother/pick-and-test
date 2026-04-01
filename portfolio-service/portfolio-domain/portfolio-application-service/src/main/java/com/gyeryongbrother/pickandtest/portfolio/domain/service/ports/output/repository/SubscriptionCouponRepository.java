package com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.repository;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.SubscriptionCoupon;
import java.util.Optional;

public interface SubscriptionCouponRepository {

    Optional<SubscriptionCoupon> findById(Long id);

    SubscriptionCoupon save(SubscriptionCoupon coupon);
}
