package com.fromdot.kafkahandson.ugc.controller;

import com.fromdot.kafkahandson.ugc.CouponIssueHistoryUsecase;
import com.fromdot.kafkahandson.ugc.ListUsableCouponUsecase;
import com.fromdot.kafkahandson.ugc.RequestCouponIssueUsecase;
import com.fromdot.kafkahandson.ugc.coupon.model.ResolvedCoupon;
import com.fromdot.kafkahandson.ugc.model.CouponDto;
import com.fromdot.kafkahandson.ugc.model.CouponIssueRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final CouponIssueHistoryUsecase couponIssueHistoryUsecase;
    private final RequestCouponIssueUsecase requestCouponIssueUsecase;
    private final ListUsableCouponUsecase listUsableCouponUsecase;

    @PostMapping
    ResponseEntity<String> issue(@RequestBody CouponIssueRequest request) {

        if(!couponIssueHistoryUsecase.hasRemainingCoupon(request.getCouponEventId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not enough available coupons!\n");
        }

        if(!couponIssueHistoryUsecase.isFirstRequestFromUser(request.getCouponEventId(), request.getUserId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Already tried to issue a coupon!\n");
        }

        requestCouponIssueUsecase.queue(request.getCouponEventId(), request.getUserId());

        return ResponseEntity.ok("Successfully issued!\n");

    }

    @GetMapping
    ResponseEntity<List<CouponDto>> listUsableCoupons(@RequestParam(name = "userId") Long userId) {
        List<ResolvedCoupon> resolvedCoupons = listUsableCouponUsecase.listByUserId(userId);
        return ResponseEntity.ok().body(resolvedCoupons.stream().map(this::toDto).toList());
    }

    private CouponDto toDto(ResolvedCoupon resolvedCoupon) {
        return new CouponDto(
                resolvedCoupon.getCoupon().getId(),
                resolvedCoupon.getCouponEvent().getDisplayName(),
                resolvedCoupon.getCouponEvent().getExpiresAt()
        );
    }

}
