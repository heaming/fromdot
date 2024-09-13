package com.fromdot.kafkahandson.ugc.port;

public interface CouponIssueRequestHistoryPort {

    boolean setHistoryIfNotExists(Long couponEventId, Long userId);

    Long getRequestSequentialNumber(Long couponEventId);

}
