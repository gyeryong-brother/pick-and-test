package com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.SubscriptionCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionCouponJpaRepository extends JpaRepository<SubscriptionCouponEntity, Long> {
}
