package com.gyeryongbrother.pickandtest.portfolio.domain.core.entity;

import java.time.LocalDateTime;

public class UserCoupon {

    private final Long id;
    private final Long userId;
    private final Long couponId;
    private final LocalDateTime issuedAt;

    public UserCoupon(Long id, Long userId, Long couponId, LocalDateTime issuedAt) {
        this.id = id;
        this.userId = userId;
        this.couponId = couponId;
        this.issuedAt = issuedAt;
    }

    public static UserCoupon create(Long userId, Long couponId) {
        return new UserCoupon(null, userId, couponId, LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }
}
