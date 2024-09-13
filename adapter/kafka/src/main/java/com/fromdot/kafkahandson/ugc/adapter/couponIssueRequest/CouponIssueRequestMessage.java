package com.fromdot.kafkahandson.ugc.adapter.couponIssueRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CouponIssueRequestMessage {
    private Long couponEventId;
    private Long userId;

    public static CouponIssueRequestMessage from(Long couponEventId, Long userId) {
        return new CouponIssueRequestMessage(couponEventId, userId);
    }


}
