package com.fromdot.kafkahandson.ugc.port;

import com.fromdot.kafkahandson.ugc.coupon.model.CouponEvent;

public interface CouponEventPort {

    CouponEvent findById(Long id);
}
