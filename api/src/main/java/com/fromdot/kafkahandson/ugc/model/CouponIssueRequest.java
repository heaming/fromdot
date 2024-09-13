package com.fromdot.kafkahandson.ugc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CouponIssueRequest {
    private Long couponEventId;
    private Long userId;
}
