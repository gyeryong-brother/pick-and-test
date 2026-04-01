package com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.UserCouponEntity;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.UserCoupon;
import org.springframework.stereotype.Component;

@Component
public class UserCouponMapper {

    public UserCoupon toDomain(UserCouponEntity entity) {
        if (entity == null) {
            return null;
        }

        return new UserCoupon(
                entity.getId(),
                entity.getUserId(),
                entity.getCouponId(),
                entity.getIssuedAt()
        );
    }

    public UserCouponEntity toEntity(UserCoupon domain) {
        if (domain == null) {
            return null;
        }

        return UserCouponEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .couponId(domain.getCouponId())
                .issuedAt(domain.getIssuedAt())
                .build();
    }
}
