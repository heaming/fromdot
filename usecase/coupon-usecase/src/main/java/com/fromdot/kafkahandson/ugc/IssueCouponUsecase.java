package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.coupon.model.Coupon;

public interface IssueCouponUsecase {

    Coupon save(Long couponEventId, Long userId);

}
