package com.fromdot.kafkahandson.ugc.port;

import com.fromdot.kafkahandson.ugc.coupon.model.Coupon;
import com.fromdot.kafkahandson.ugc.coupon.model.ResolvedCoupon;

import java.util.List;

public interface CouponPort {

    Coupon save(Coupon coupon);
    List<ResolvedCoupon> listByUserId(Long userId);
}
