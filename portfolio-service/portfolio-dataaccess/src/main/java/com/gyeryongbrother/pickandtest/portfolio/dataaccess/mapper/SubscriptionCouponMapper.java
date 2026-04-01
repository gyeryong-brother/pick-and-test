package com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.SubscriptionCouponEntity;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.SubscriptionCoupon;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionCouponMapper {

    public SubscriptionCoupon toDomain(SubscriptionCouponEntity entity) {
        if (entity == null) {
            return null;
        }

        return new SubscriptionCoupon(
                entity.getId(),
                entity.getName(),
                entity.getTotalQuantity(),
                entity.getRemainingQuantity(),
                entity.getExpiresAt(),
                entity.isActive()
        );
    }

    public SubscriptionCouponEntity toEntity(SubscriptionCoupon domain) {
        if (domain == null) {
            return null;
        }

        return SubscriptionCouponEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .totalQuantity(domain.getTotalQuantity())
                .remainingQuantity(domain.getRemainingQuantity())
                .expiresAt(domain.getExpiresAt())
                .active(domain.isActive())
                .build();
    }
}
