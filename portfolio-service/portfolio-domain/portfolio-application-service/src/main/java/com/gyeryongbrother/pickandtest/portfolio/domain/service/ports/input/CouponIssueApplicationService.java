package com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.CouponIssueCommand;

public interface CouponIssueApplicationService {

    void issueSubscriptionCoupon(CouponIssueCommand command);
}
