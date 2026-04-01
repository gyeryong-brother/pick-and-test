package com.gyeryongbrother.pickandtest.portfolio.domain.core.entity;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.exception.CouponCoreException;
import java.time.LocalDateTime;

public class SubscriptionCoupon {

    private final Long id;
    private final String name;
    private final int totalQuantity;
    private int remainingQuantity;
    private final LocalDateTime expiresAt;
    private boolean active;

    public SubscriptionCoupon(
            Long id,
            String name,
            int totalQuantity,
            int remainingQuantity,
            LocalDateTime expiresAt,
            boolean active
    ) {
        this.id = id;
        this.name = name;
        this.totalQuantity = totalQuantity;
        this.remainingQuantity = remainingQuantity;
        this.expiresAt = expiresAt;
        this.active = active;
    }

    public void issue() {
        if (!active) {
            throw new CouponCoreException("This coupon is not active or has been disabled.");
        }
        if (LocalDateTime.now().isAfter(expiresAt)) {
            throw new CouponCoreException("This coupon has expired.");
        }
        if (remainingQuantity <= 0) {
            throw new CouponCoreException("This coupon is fully exhausted (Out of stock).");
        }
        
        this.remainingQuantity--;
        
        if (this.remainingQuantity == 0) {
            this.active = false;
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public boolean isActive() {
        return active;
    }
}
