package com.fromdot.kafkahandson.ugc.port;

public interface CouponIssueRequestPort {

    void sendMessage(Long couponEventId, Long userId);
}
