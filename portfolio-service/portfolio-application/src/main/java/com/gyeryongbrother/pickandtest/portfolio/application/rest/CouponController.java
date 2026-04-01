package com.gyeryongbrother.pickandtest.portfolio.application.rest;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.exception.CouponCoreException;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.CouponIssueCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input.CouponIssueApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponIssueApplicationService couponIssueApplicationService;

    @PostMapping("/{couponId}/issue")
    public ResponseEntity<String> issueCoupon(
            @PathVariable Long couponId,
            @RequestParam Long userId) {
        
        try {
            CouponIssueCommand command = CouponIssueCommand.builder()
                    .couponId(couponId)
                    .userId(userId)
                    .build();
            
            couponIssueApplicationService.issueSubscriptionCoupon(command);
            
            return ResponseEntity.ok("Coupon successfully issued.");
        } catch (CouponCoreException e) {
            log.warn("Coupon issue failed for user {}: {}", userId, e.getMessage());
            return ResponseEntity.badRequest().body("Failed to issue coupon: " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error during coupon issue", e);
            return ResponseEntity.internalServerError().body("An unexpected error occurred.");
        }
    }
}
