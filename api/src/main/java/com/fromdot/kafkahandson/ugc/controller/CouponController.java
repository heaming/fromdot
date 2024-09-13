package com.fromdot.kafkahandson.ugc.controller;

import com.fromdot.kafkahandson.ugc.CouponIssueHistoryUsecase;
import com.fromdot.kafkahandson.ugc.RequestCouponIssueUsecase;
import com.fromdot.kafkahandson.ugc.model.CouponIssueRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final CouponIssueHistoryUsecase couponIssueHistoryUsecase;
    private final RequestCouponIssueUsecase requestCouponIssueUsecase;

    @PostMapping
    ResponseEntity<String> issue(@RequestBody CouponIssueRequest request) {

        if(!couponIssueHistoryUsecase.isFirstRequestFromUser(request.getCouponEventId(), request.getUserId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Already tried to issue a coupon!\n");
        }

        if(!couponIssueHistoryUsecase.hasRemainingCoupon(request.getCouponEventId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not enough available coupons!\n");
        }

        requestCouponIssueUsecase.queue(request.getCouponEventId(), request.getUserId());

        return ResponseEntity.ok("Successfully issued!\n");

    }


}
