package com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.UserCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCouponJpaRepository extends JpaRepository<UserCouponEntity, Long> {

    boolean existsByUserIdAndCouponId(Long userId, Long couponId);
}
