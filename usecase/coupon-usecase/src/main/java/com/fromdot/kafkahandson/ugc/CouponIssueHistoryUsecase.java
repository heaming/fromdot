package com.fromdot.kafkahandson.ugc;


public interface CouponIssueHistoryUsecase {

    // 중복 요청 확인
    boolean isFirstRequestFromUser(Long couponEventId, Long userId);

    // 쿠폰 잔여 수량 확인
    boolean hasRemainingCoupon(Long couponEventId);
}
