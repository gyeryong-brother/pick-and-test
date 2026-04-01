package com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.SubscriptionCouponEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper.SubscriptionCouponMapper;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.SubscriptionCouponJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.SubscriptionCoupon;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.repository.SubscriptionCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SubscriptionCouponRepositoryImpl implements SubscriptionCouponRepository {

    private final SubscriptionCouponJpaRepository jpaRepository;
    private final SubscriptionCouponMapper mapper;

    @Override
    public Optional<SubscriptionCoupon> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public SubscriptionCoupon save(SubscriptionCoupon coupon) {
        SubscriptionCouponEntity entity = mapper.toEntity(coupon);
        SubscriptionCouponEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}
