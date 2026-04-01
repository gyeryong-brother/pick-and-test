package com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.UserCouponEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper.UserCouponMapper;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.UserCouponJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.UserCoupon;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.repository.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCouponRepositoryImpl implements UserCouponRepository {

    private final UserCouponJpaRepository jpaRepository;
    private final UserCouponMapper mapper;

    @Override
    public boolean existsByUserIdAndCouponId(Long userId, Long couponId) {
        return jpaRepository.existsByUserIdAndCouponId(userId, couponId);
    }

    @Override
    public UserCoupon save(UserCoupon userCoupon) {
        UserCouponEntity entity = mapper.toEntity(userCoupon);
        UserCouponEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }
}
