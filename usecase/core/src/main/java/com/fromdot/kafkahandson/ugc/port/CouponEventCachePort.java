package com.fromdot.kafkahandson.ugc.port;

import com.fromdot.kafkahandson.ugc.coupon.model.CouponEvent;

public interface CouponEventCachePort {

    void set(CouponEvent couponEvent);
    CouponEvent get(Long couponEventId);
}
