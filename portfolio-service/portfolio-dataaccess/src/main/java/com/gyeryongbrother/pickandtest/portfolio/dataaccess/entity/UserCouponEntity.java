package com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_coupons")
public class UserCouponEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long couponId;
    private LocalDateTime issuedAt;

    @Builder
    public UserCouponEntity(Long id, Long userId, Long couponId, LocalDateTime issuedAt) {
        this.id = id;
        this.userId = userId;
        this.couponId = couponId;
        this.issuedAt = issuedAt;
    }
}
