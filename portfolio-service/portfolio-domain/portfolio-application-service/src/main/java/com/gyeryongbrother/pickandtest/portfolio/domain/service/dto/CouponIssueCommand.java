package com.gyeryongbrother.pickandtest.portfolio.domain.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CouponIssueCommand {
    private final Long couponId;
    private final Long userId;
}
