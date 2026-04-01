package com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.repository;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.UserCoupon;

public interface UserCouponRepository {

    boolean existsByUserIdAndCouponId(Long userId, Long couponId);

    UserCoupon save(UserCoupon userCoupon);
    
    long count();
}
