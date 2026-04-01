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
@Table(name = "subscription_coupons")
public class SubscriptionCouponEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int totalQuantity;
    private int remainingQuantity;
    private LocalDateTime expiresAt;
    private boolean active;

    @Builder
    public SubscriptionCouponEntity(Long id, String name, int totalQuantity, int remainingQuantity, LocalDateTime expiresAt, boolean active) {
        this.id = id;
        this.name = name;
        this.totalQuantity = totalQuantity;
        this.remainingQuantity = remainingQuantity;
        this.expiresAt = expiresAt;
        this.active = active;
    }
}
